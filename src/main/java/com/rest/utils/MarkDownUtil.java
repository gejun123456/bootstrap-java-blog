package com.rest.utils;

import com.rest.constant.MarkDownConstant;
import org.apache.commons.lang3.StringEscapeUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownUtil {

    private static Logger logger = LoggerFactory.getLogger(MarkDownUtil.class);

    private static Queue<MarkdownRequest> toHandleRequest = new LinkedBlockingQueue<>(10);

    //every map get it's shall remove for a long time not been used.
    private static Map<Long, String> result = new HashMap<Long, String>();

    //    private static PegDownProcessor processor = new PegDownProcessor();
    private static Context context;

    public static Object lock = new Object();

    private static Scriptable scope;

    //正常应该没有并发问题。
    private static AtomicLong index = new AtomicLong(0);

    //new to manipute with thread.
//    shall design a thread to do the thing.
    static {
        //need to check the data in the map every day  in case result is not grabbed by user. the map will get bigger.
        new Thread(new Runnable() {
            @Override
            public void run() {
                context = Context.enter();
                scope = context.initStandardObjects();
                InputStreamReader showdownreader = new InputStreamReader(MarkDownUtil.class.getResourceAsStream("/static/js/showdown.js"));
                try {
                    context.evaluateReader(scope, showdownreader, "showdown.js", 1, null);
                } catch (IOException e) {
                    logger.error("showdown js init failed", e);
                }
                Object wrappedOut = Context.javaToJS(System.out, scope);
                ScriptableObject.putProperty(scope, "out", wrappedOut);
                String code = "var converter = new showdown.Converter();";
                context.evaluateString(scope, code, "<mem>", 1, null);
                while (true) {
                    synchronized (lock) {
                        while (toHandleRequest.isEmpty()) {
                            try {
                                lock.wait();
                            } catch (InterruptedException e) {
                                logger.error("markDownUtil catch interrupted exception");
                            }

                        }
                    }
                    while (!toHandleRequest.isEmpty()) {
                        MarkdownRequest request = toHandleRequest.poll();
                        String content = StringEscapeUtils.escapeEcmaScript(request.getContent());
                        Object o = context.evaluateString(scope, "converter.makeHtml('" + content + "')", content, 1, null);
                        //可能两次放了相同的进来, 最好定一个只
                        //又客户端来生成对应的count.
                        result.put(request.getCount(), context.toString(o));
                        //this could waste time for latter execution.
                        synchronized (request.getCount()) {
                            request.getCount().notify();
                        }
                    }
                }
            }
        }).start();

    }


    public static void addRequest(MarkdownRequest request) {
        toHandleRequest.add(request);
    }


    //this is the syconized method for things to do.
    public static String convertToHtml(String content) {
        //try to notify the sever to do with it's thing.
        MarkdownRequest request = new MarkdownRequest();
        request.setCount(index.getAndIncrement());
        request.setContent(content);
        //shall synchronized before send request. the notify might do before wait(can't let it happen).
        synchronized (request.getCount()) {
            addRequest(request);
            synchronized (lock) {
                lock.notify();
            }
            while (!result.containsKey(request.getCount())) {
                try {
                    request.getCount().wait();
                } catch (InterruptedException e) {
                    logger.error("get result cause Interrupted Exception", e);
                }
            }

            String s = result.get(request.getCount());
            result.remove(request.getCount());
            return s;
        }
    }

    public static void destroy() {
        if (context != null) {
            context.exit();
        }
    }

    //remove markdown syntax on things.
    public static String removeMark(String sourceContent) {
        sourceContent = sourceContent.replace(MarkDownConstant.MORE, "");
        //将连接和图片中的东西给搞定
        //todo  if such []() exist in code the code will be deleted. so much thing to delete.
        //shall check with them.
        sourceContent = extract(sourceContent);
        StringBuilder res = new StringBuilder();
        char last = ' ';
        for (int i = 0; i < sourceContent.length(); i++) {
            char c = sourceContent.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                res.append(c);
                last = c;
            } else {
                if (last != ' ') {
                    res.append(' ');
                }
                last = ' ';
            }
        }
        return res.toString();
    }

    public static String extract(String s) {
        int status = 0;
        int start = 0;
        String result = "";
        int matchstart = -1;
        int matchEnd = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (status) {
                case 0:
                    if (c == '[') {
                        status = 1;
                        matchstart = i;
                    } else {
                        result += s.substring(start, i + 1);
                        start = i + 1;
                    }
                    break;
                case 1:
                    if (c == ']') {
                        status = 2;
                        matchEnd = i;
                    } else if (c == '[') {
                        status = 1;
                        result += s.substring(start, i);
                        start = i;
                        matchstart = i;
                    } else {
                        //this time this is safe.
                        status = 1;
                    }
                    break;
                case 2:
                    if (c == '(') {
                        status = 3;
                    } else if (c == ']') {
                        status = 2;
                        matchEnd = i;
                    } else if (c == '[') {
                        status = 1;
                        matchstart = i;
                        result += s.substring(start, i);
                        start = i;
                    } else {
                        status = 0;
                        result += s.substring(start, i + 1);
                        start = i + 1;
                    }
                    break;
                case 3:
                    if (c == ')') {
                        //find a match store the value into it.
                        result += s.substring(matchstart + 1, matchEnd);
                        status = 0;
                        start = i + 1;
                    } else {
                        //无法回头.
                        status = 3;
                    }
                    break;
            }
            //check the final status.
        }
        if (status != 3) {
            result += s.substring(start);
        }
        return result;
    }
}
