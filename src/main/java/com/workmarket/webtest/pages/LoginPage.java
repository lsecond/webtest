package com.workmarket.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends AbstractPage{

    @FindBy(id = "login-email")
    private WebElement emailAddressField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login_page_button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public boolean inputEmail(String email) {
        log.info(String.format("Input email: %s", email));
        return sendKeys(emailAddressField, email);
    }

    public boolean inputPassword(String password){
        log.info(String.format("Input password: %s", password));
        return sendKeys(passwordField, password);
    }

    public boolean clickLogin() {
        log.info("Click on login button");
        return clickElement(loginButton);
    }
}
