package com.rest.jsoup;

import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;
import org.junit.Test;

/**
 * @Author bruce.ge
 * @Date 2017/1/20
 * @Description
 */
public class XssTest {
    @Test
    public void test(){
        String clean = Jsoup.clean("<code><script>nima</script></code>", Whitelist.basic());
        System.out.println(clean);


        String clean2 = Jsoup.clean("hello <a name=\"n\" href=\"javascript:alert('xss')\">*you*</a>", Whitelist.basic());
        boolean valid = Jsoup.isValid("hello <a name=\"n\" href=\"javascript:alert('xss')\">*you*</a>", Whitelist.basic());
        System.out.println(valid);
        System.out.println(clean2);
    }
}
