package com.workmarket.webtest.tests;

import com.workmarket.webtest.pages.WorkMarket;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public abstract class AbstractTest {

    public enum UserEnum {

        VALID_USER("qa+candidatetest@workmarket.com", "candidate123"),
        WRONG_USER("wronguser@gmail.com", "automation"),
        EMPTY_PASSWORD_USER(" ", "automation"),
        EMPTY_EMIAL_USER(" ", "automation");

        public final String email;
        public final String password;

        UserEnum(String email, String password) {
            this.email = email;
            this.password = password;
        }
    }

    public static final String loginUrl = "https://dev.workmarket.com/login";

    public static final Logger log = Logger.getLogger(Class.class.getName());

    protected static WebDriver driver;

    private static WorkMarket web;

    public static WorkMarket getWeb() {
        return web;
    }

    @BeforeClass
    public static void beforeClassAbstractTest() {
        if(System.getProperty("os.name").contains("Mac")){
            System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        } else {
            System.setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");
        }
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        AbstractTest.web = new WorkMarket(driver);
        driver.get(loginUrl);
    }

    @AfterClass
    public static void afterClassAbstractTests() {
        if (driver != null) {
            System.out.println("Close driver");
            driver.close();
            driver.quit();
        }
    }

    public static Collection<Object[]> parametersInjection(Object[] paramsToInject) {
        final Collection<Object[]> result = new ArrayList<>();
        for (Object e : paramsToInject) {
            result.add(new Object[]{e});
        }
        return result;
    }
}
