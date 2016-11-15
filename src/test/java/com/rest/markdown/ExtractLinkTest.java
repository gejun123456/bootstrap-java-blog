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
        int startOne = -1;
        int startTwo = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            switch (status) {
                case 0:
                    if (c == '[') {
                        status = 1;
                        startOne = i;
                    } else {
                        result += s.substring(start, i + 1);
                        start = i + 1;
                    }
                    break;
                case 1:
                    if(c==']'){
                        status=2;
                        startTwo=i;
                    } else if(c=='['){
                        status=1;
                        result+=s.substring(start,i);
                        start=i;
                        startOne=i;
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
                        startTwo=i;
                    } else if(c=='['){
                        status=1;
                        startTwo=-1;
                        startOne=i;
                        result+=s.substring(start,i);
                        start=i;
                    } else {
                        status=0;
                        startOne=-1;
                        startTwo=-1;
                        result+=s.substring(start,i+1);
                        start=i+1;
                    }
                    break;
                case 3:
                    if(c==')'){
                        //find a match store the value into it.
                        result+=s.substring(startOne+1,startTwo);
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
    }
}
