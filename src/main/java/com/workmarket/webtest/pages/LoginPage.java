package com.workmarket.webtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {

    @FindBy(id = "login-email")
    private WebElement emailAddressField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login_page_button")
    private WebElement loginButton;

    public void inputEmail(String email) {
       emailAddressField.sendKeys(email);
    }

    public void inputPassword(String password){
        passwordField.sendKeys(password);
    }

    public void clickLogin() {
        loginButton.click();
    }
}
