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

    //remove markdown syntax on things.
    public static String removeMark(String sourceContent) {
        StringBuilder res = new StringBuilder();
        char last = ' ';
        for (int i = 0; i < sourceContent.length(); i++) {
            char c = sourceContent.charAt(i);
            if(Character.isLetterOrDigit(c)){
                res.append(c);
                last = c;
            } else {
                if(last!=' '){
                    res.append(' ');
                }
                last = ' ';
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        System.out.println(removeMark("###helllo#"));
        System.out.println(removeMark("[hello]"));
    }



}
