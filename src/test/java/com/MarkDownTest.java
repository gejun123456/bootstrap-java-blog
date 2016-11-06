package com;

import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.AttributeProvider;
import org.commonmark.renderer.html.AttributeProviderContext;
import org.commonmark.renderer.html.AttributeProviderFactory;
import org.commonmark.renderer.html.HtmlRenderer;
import org.junit.Test;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class MarkDownTest {
    @Test
    public void testMarkDown(){
        Parser parser = Parser.builder().build();
        Node parse = parser.parse("some code for java springboot usage.\n" +
                "\n" +
                "\n" +
                "     @RequestMapping(\"/greeting\")\n" +
                "    public Greeting greeting(@RequestParam(value = \"name\",defaultValue = \"world\")String name){\n" +
                "        logger.error(name);\n" +
                "        return new Greeting(counter.incrementAndGet(),String.format(template,name));\n" +
                "    }\n" +
                "\n" +
                "    @RequestMapping(\"/getCustomer\")\n" +
                "    public CustomerDto getCustomer(@RequestParam(value = \"id\") int id){\n" +
                "        Customer customer = customerMapper.selectById(id);\n" +
                "        return BaseConverter.convert(customer);\n" +
                "    }");
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        String render = renderer.render(parse);
        System.out.println(render);

    }
}
