package com;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by bruce.ge on 2016/11/6.
 */
public class PropertyTest {
    @Test
    public void testPropertis(){
        File file = new File("\\env\\databaseconfig");
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream(file));
            System.out.println(prop.getProperty("username"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
