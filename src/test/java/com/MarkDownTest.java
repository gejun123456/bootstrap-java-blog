package com;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;
import org.pegdown.PegDownProcessor;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownTest {
    @Test
    public void testMarkDown(){
        Parser parser = Parser.builder().build();
        Node parse = parser.parse("#you know it");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String render = renderer.render(parse);
        System.out.println(render);

    }

    @Test
    public void markDownWithPeg(){
        PegDownProcessor processor = new PegDownProcessor();
        String s = processor.markdownToHtml("###youknowit");
        System.out.println(s);
    }
}
