package com;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;
import org.pegdown.PegDownProcessor;

import java.io.File;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownTest {
    @Test
    public void testMarkDown(){
        Parser parser = Parser.builder().build();
        Node parse = parser.parse("我靠你妹<!–more–>日乐购");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String render = renderer.render(parse);
        System.out.println(render);

    }

    @Test
    public void markDownWithPeg(){
        PegDownProcessor processor = new PegDownProcessor();
        String s = processor.markdownToHtml("我靠你妹<!–more–>日乐购");
        System.out.println(s);
        System.out.println(File.separator);
    }

    @Test
    public void testReplace(){
        String s = "你妹呀<!-more->为啥不行 日了狗";
        String replace = s.replace("<!-more->", "");
        System.out.println(replace);
    }

    @Test
    public void testIndexOf(){
        String m = "你好呀 <!-more-> bitch ge";
        System.out.println(m.indexOf("<!-more->"));
    }
}
