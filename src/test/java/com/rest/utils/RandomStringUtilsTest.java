package com.rest.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

/**
 * Created by bruce.ge on 2016/11/15.
 */
public class RandomStringUtilsTest {
    @Test
    public void test(){
        String random = RandomStringUtils.random(33, true, true);
        System.out.println(random);
        System.out.println(RandomStringUtils.random(33));
        String m ="햽쬅員鄇䑓躲\uE1E9ඪ뚪❮䉘ퟏꉞ\uE5FC\uD854\uDC40봉ꨥ莪ὠᢕ\uD902\uDC42훽㚣⧚贃㋉\uE121꼷葂⥮뗱";
        System.out.println(m.length());
    }
}
