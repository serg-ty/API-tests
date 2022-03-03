package com.demo.conf;

import java.io.IOException;

public class Config {

    static {
        try {
            System.getProperties().load(ClassLoader.getSystemResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getProperty(String property) {
        return System.getProperty(property);
    }
}
