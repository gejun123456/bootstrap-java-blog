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
    }
}
