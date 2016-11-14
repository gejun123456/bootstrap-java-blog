package com.rest.utils;

import com.alibaba.druid.support.logging.Log;
import com.rest.constant.MarkDownConstant;
import org.apache.commons.lang3.StringEscapeUtils;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.pegdown.PegDownProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownUtil {

    private static Logger logger = LoggerFactory.getLogger(MarkDownUtil.class);

    private static Queue<MarkdownRequest>  toHandleRequest = new LinkedBlockingQueue<>(10);

    //every map get it's shall remove for a long time not been used.
    private static Map<Long,String> result = new HashMap<Long,String>();

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
                    logger.error("showdown js init failed",e);
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
                    while(!toHandleRequest.isEmpty()){
                        MarkdownRequest request = toHandleRequest.poll();
                        String content = StringEscapeUtils.escapeEcmaScript(request.getContent());
                        Object o = context.evaluateString(scope, "converter.makeHtml('" + content + "')", content, 1, null);
                        //可能两次放了相同的进来, 最好定一个只
                        //又客户端来生成对应的count.
                        result.put(request.getCount(),context.toString(o));
                        synchronized (request.getCount()){
                            request.getCount().notify();
                        }
                    }
                }
            }
        }).start();

    }

    public static Long sendRequest(String text){
        MarkdownRequest request = new MarkdownRequest();
        request.setCount(index.getAndIncrement());
        request.setContent(text);
        addRequest(request);
        return request.getCount();
    }
    public static void addRequest(MarkdownRequest request){
        toHandleRequest.add(request);
    }

//to get result.
    public static String getResult(Long count){
        //shall use wth wait.
        synchronized (count) {
            while (!result.containsKey(count)) {
                try {
                    count.wait();
                } catch (InterruptedException e) {
                    logger.error("get result cause Interrupted Exception", e);
                }
            }
        }

        String s = result.get(count);
        result.remove(count);
        return s;

    }

//this is the syconized method for things to do.
    public static String convertToHtml(String content) {
        //try to notify the sever to do with it's thing.
        Long aLong = sendRequest(content);
        synchronized (lock){
            lock.notify();
        }
        return getResult(aLong);
    }

    public  static void destroy(){
        if(context!=null){
            context.exit();
        }
    }

    //remove markdown syntax on things.
    public static String removeMark(String sourceContent) {
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
}
