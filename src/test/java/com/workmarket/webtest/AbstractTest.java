package com.workmarket.webtest;

import com.workmarket.webtest.pages.WorkMarket;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.logging.Logger;

public abstract class AbstractTest {

    public final static String USER_EMAIL =  "qa+candidatetest@workmarket.com";
    public final static String USER_PASSWORD = "candidate123";

    public static final Logger log = Logger.getLogger(Class.class.getName());

    protected static WebDriver driver;

    private static WorkMarket web;

    public static WorkMarket getWeb() {
        return web;
    }

    @BeforeClass
    public static void beforeClassAbstractTest() {
        driver = new ChromeDriver();
        AbstractTest.web = new WorkMarket(driver);
        String url = "https://dev.workmarket.com/login";
        driver.get(url);
    }

    @AfterClass
    public static void afterClassAbstractTests() {
        if (driver != null) {
            System.out.println("Close driver");
            driver.close();
            driver.quit();
        }
    }

    public static void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            log.severe(e.toString());
        }
    }
}
