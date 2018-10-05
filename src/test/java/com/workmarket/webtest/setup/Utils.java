package com.workmarket.webtest.setup;

public class Utils {

    public static void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
        }
    }
}
