package com.rest.utils;

import com.google.gson.Gson;

/**
 * Created by bruce.ge on 2016/10/23.
 */
public class GsonUtils {
    private static Gson gson  =new Gson();

    public static void printToGson(Object o){
        System.out.println(gson.toJson(o));
    }
}
