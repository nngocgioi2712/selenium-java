package com.vmo.demowebshop.utils;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
    private static Logger logger = LogManager.getLogger(Log.class);

    public static void info(String message, String... params) {

        logger.info(String.format(message, (Object[]) params));
    }

    public static void error(String message, String... params) {
        logger.error(String.format(message, (Object[]) params));
    }

    public static void debug(String message, String... params) {
        logger.debug(String.format(message, (Object[]) params));
    }

    public static void allure(String message, String... params) {
        logger.info(String.format(message, (Object[]) params));
        step(String.format(message, (Object[]) params));
    }

    @Step("{msg}")
    private static void step(String msg) {
    }
}
