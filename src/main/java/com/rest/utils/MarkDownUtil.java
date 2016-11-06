package com.rest.utils;

import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.pegdown.PegDownProcessor;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownUtil {

    private static PegDownProcessor processor = new PegDownProcessor();

    public static String convertToHtml(String content){
        return processor.markdownToHtml(content);
    }
}
