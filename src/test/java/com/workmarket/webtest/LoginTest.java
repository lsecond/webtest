package com.workmarket.webtest;

import com.workmarket.webtest.pages.LoginPage;
import com.workmarket.webtest.setup.Utils;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.openqa.selenium.support.PageFactory.initElements;

public class LoginTest {

    static WebDriver driver;
    LoginPage loginPage = initElements(driver, LoginPage.class);

    @BeforeClass
    public static void beforeClassLoginTest(){
        driver = new ChromeDriver();
        String url = "https://dev.workmarket.com/login";
        driver.get(url);
    }

    @Test
    public void normalLogin() {
        loginPage.inputEmail("1809673@qq.com");
        Utils.sleep(3000);
        loginPage.inputPassword("1dtjcjsh");
        Utils.sleep(3000);
        loginPage.clickLogin();
        Utils.sleep(10000);

    }

    @AfterClass
    public static void afterClassLoginTest(){
        if (driver != null) {
            System.out.println("Close driver");
            driver.close();
            driver.quit();
        }
    }
}
