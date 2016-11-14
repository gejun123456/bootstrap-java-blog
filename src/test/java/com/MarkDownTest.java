package com;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.pegdown.PegDownProcessor;

import java.io.File;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import static org.mozilla.javascript.ScriptableObject.PERMANENT;
import static org.mozilla.javascript.ScriptableObject.READONLY;

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


    @Test
    public void withRhino(){
        try {
            Context context = Context.enter();
            Scriptable scope = context.initStandardObjects();
            InputStreamReader showdownreader = new InputStreamReader(getClass().getResourceAsStream("/static/js/showdown.js"));
            context.evaluateReader(scope,showdownreader,"showdown.js",1,null);
            Object wrappedOut = Context.javaToJS(System.out, scope);
            ScriptableObject.putProperty(scope,"out",wrappedOut);
            String code = "var converter = new showdown.Converter();";
            context.evaluateString(scope, code, "<mem>", 1, null);
            Object o2 = context.evaluateString(scope, "converter.makeHtml('(![](http://localhost:8080/files/1479141611049IMG_20161023_111154.jpg =100x*)\n" +
                    "价格more <!-more-> 呵呵 不信不行')", "nima", 1, null);
            System.out.println(context.toString(o2));

//            Context context = Context.enter();
//            Scriptable globalScope = context.initStandardObjects();
//            Reader esprimaLibReader = new InputStreamReader(getClass().getResourceAsStream("/static/js/esprima.js"));
//            context.evaluateReader(globalScope, esprimaLibReader, "esprima.js", 1, null);
//
//// Add a global variable out that is a JavaScript reflection of the System.out variable:
//            Object wrappedOut = Context.javaToJS(System.out, globalScope);
//            ScriptableObject.putProperty(globalScope, "out", wrappedOut);
//
//            String code = "var syntax = esprima.parse('42');" +
//                    "out.print(JSON.stringify(syntax, null, 2));";

// The module esprima is available as a global object due to the same
// scope object passed for evaluation:
//            context.evaluateString(globalScope, code, "<mem>", 1, null);
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            Context.exit();
        }

    }
}
