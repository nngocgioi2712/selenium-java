package com.vmo.demowebshop.utils;

import com.github.automatedowl.tools.AllureEnvironmentWriter;
import com.google.common.collect.ImmutableMap;

public class AllureManager {
    public AllureManager() {}

    public static ImmutableMap.Builder<String, String> environmentMapBuilder = new ImmutableMap.Builder<>();
    public static void setAllureEnvironmentInformation(String key, String value) {
        environmentMapBuilder.put(key, value);
    }
    public static void writeAllureEnvironmentInformation() {
        AllureEnvironmentWriter.allureEnvironmentWriter(
                environmentMapBuilder
                        .build(), System.getProperty("user.dir") + "/target/allure-results/");
    }
}
