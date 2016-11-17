package com.rest.i18n;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by bruce.ge on 2016/11/17.
 */
public class I18NSample {
    public static void main(String[] args) {
        String language;
        String country;
        if(args.length!=2){
            language = new String("de");
            country = new String("DE");
        } else {
            language = new String(args[0]);
            country=new String(args[1]);
        }
        Locale currentLocal;
        ResourceBundle messages;
        currentLocal = new Locale(language,country);
        messages = ResourceBundle.getBundle("MessagesBundle",currentLocal);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));

        currentLocal = Locale.SIMPLIFIED_CHINESE;

        messages = ResourceBundle.getBundle("MessagesBundle",currentLocal);
        System.out.println(messages.getString("greetings"));
        System.out.println(messages.getString("inquiry"));
        System.out.println(messages.getString("farewell"));
    }
}
