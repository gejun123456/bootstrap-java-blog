package com.rest.utils;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownUtil {

    private static Parser parser = Parser.builder().build();

    private static HtmlRenderer renderer = HtmlRenderer.builder().build();

    public static String convertToHtml(String content){
        return renderer.render(parser.parse(content));
    }
}
