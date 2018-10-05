package com.workmarket.webtest;

import com.workmarket.webtest.pages.FindTalentPage;
import com.workmarket.webtest.pages.LoginPage;
import com.workmarket.webtest.pages.MainPage;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.workmarket.webtest.setup.Constants.USER_EMAIL;
import static com.workmarket.webtest.setup.Constants.USER_PASSWORD;
import static org.openqa.selenium.support.PageFactory.initElements;

public class MainPageSearch {

    static WebDriver driver;
    LoginPage loginPage = initElements(driver, LoginPage.class);
    MainPage mainPage = initElements(driver, MainPage.class);
    FindTalentPage findTalentPage = initElements(driver, FindTalentPage.class);
    final String searchText = "test";

    @BeforeClass
    public static void beforeClassMainPageTest() {
        driver = new ChromeDriver();
        String url = "https://dev.workmarket.com/login";
        driver.get(url);

    }

    @Before
    public void beforeMainPageSearch(){
        loginPage.inputEmail(USER_EMAIL);
        loginPage.inputPassword(USER_PASSWORD);
        loginPage.clickLogin();
    }

    @Test
    public void searchForTalent() {
        mainPage.clickFindTalent();
        findTalentPage.inputSearchText(searchText);
        findTalentPage.verifyAllResult(searchText);
    }

    @AfterClass
    public static void afterClassLoginTest() {
        if (driver != null) {
            System.out.println("Close driver");
            driver.close();
            driver.quit();
        }
    }
}
