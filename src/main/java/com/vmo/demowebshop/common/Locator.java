package com.vmo.demowebshop.common;

public class Locator {
    public static String id(String locator) {
        return Prefix.ID + locator;
    }
    public static String className(String locator) {
        return Prefix.CLASS + locator;
    }
    public static String name(String locator) {
        return Prefix.NAME + locator;
    }
    public static String css(String locator) {
        return Prefix.CSS + locator;
    }
    public static String xpath(String locator) {
        return Prefix.XPATH + locator;
    }
}
