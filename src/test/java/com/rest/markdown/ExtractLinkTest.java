package com.rest.markdown;

import org.assertj.core.api.Assertions;
import org.junit.Test;

/**
 * Created by bruce.ge on 2016/11/16.
 */
public class ExtractLinkTest {
    public String extract(String s) {
        int status = 0;
        int start = 0;
        String result = "";
        int matchstart = -1;
        int matchEnd = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (status) {
                case 0:
                    if (c == '[') {
                        status = 1;
                        matchstart = i;
                    } else {
                        result += s.substring(start, i + 1);
                        start = i + 1;
                    }
                    break;
                case 1:
                    if(c==']'){
                        status=2;
                        matchEnd=i;
                    } else if(c=='['){
                        status=1;
                        result+=s.substring(start,i);
                        start=i;
                        matchstart=i;
                    } else {
                    //this time this is safe.
                        status=1;
                    }
                    break;
                case 2:
                    if(c=='('){
                        status=3;
                    } else if(c==']'){
                        status=2;
                        matchEnd=i;
                    } else if(c=='['){
                        status=1;
                        matchstart=i;
                        result+=s.substring(start,i);
                        start=i;
                    } else {
                        status=0;
                        result+=s.substring(start,i+1);
                        start=i+1;
                    }
                    break;
                case 3:
                    if(c==')'){
                        //find a match store the value into it.
                        result+=s.substring(matchstart+1,matchEnd);
                        status=0;
                        start=i+1;
                    } else{
                        //无法回头.
                        status=3;
                    }
                    break;
            }
            //check the final status.
        }
        if(status!=3){
            result+=s.substring(start);
        }
        return result;
    }

    @Test
    public void test() {
        Assertions.assertThat(extract("[][[lllll]((http://www.baidu.com0[hello](hahah)")).isEqualTo("[][lllll");
        Assertions.assertThat(extract("[a [bcd](www.baidu.com)")).isEqualTo("[a bcd");
        Assertions.assertThat(extract("[abcd](www.baidu.com)")).isEqualTo("abcd");
        System.out.println(extract("我日了狗，看行不行再说\n" +
                "别给我搜出来呀\n" +
                "![](/files/1479239072199IMG_20161023_111154.jpg =200x*)\n" +
                "<!-more->\n" +
                "相当蛋疼[http://www.baidu.com](http://www.baidu.com)"));

        Assertions.assertThat(extract("![](/files/1479239072199IMG_20161023_111154.jpg =200x*)")).isEqualTo("!");
    }
}
