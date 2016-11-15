package com.rest.markdown;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by bruce.ge on 2016/11/15.
 */
public class MarkDownMatcherTest {
    @Test
    public void testReplace(){
        String m = "wokao taiceleba\n" +
                "![](/files/1479225098974IMG_20161023_111154.jpg =200x*)\n" +
                "\n" +
                "nimaya baozha\n" +
                "\n" +
                "[http://www.baidu.com](http://www.baidu.com)";

        String m_a = "[htpp://]](www.aaa.com)";

        //write the compiler of your own. check with it.

        Pattern pattern = Pattern.compile("\\[.*\\]\\(.*\\)");
        Matcher matcher = pattern.matcher(m);
        String u = "";
        int end =0;
        //先完成所有去除
        System.out.println(m.length());
        while(matcher.find()){
            int start = matcher.start();
            u+=m.substring(end,start);
            end = matcher.end();
        }
        //去除了图片的效果。how to deal with better.
        u+=m.substring(end);
        System.out.println(u);
    }
}
