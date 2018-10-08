package com.workmarket.webtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class LoginPage extends AbstractPage{

    @FindBy(id = "login-email")
    private WebElement emailAddressField;

    @FindBy(id = "login-password")
    private WebElement passwordField;

    @FindBy(id = "login_page_button")
    private WebElement loginButton;

    @FindBy(id= "login-messages")
    private WebElement loginMessage;

    @FindBy(id = "recaptcha-anchor")
    private WebElement notRobotRecaptcha;

    @FindBy(id = "reset-password")
    private WebElement resetPassword;


    @FindBy(id = "email")
    private WebElement forgotPasswordEmail;

    @FindBy(xpath = "//*[@id='reset-submit']/span")
    private WebElement resetPasswordButton;


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

    public boolean clickResetPassword() {
        log.info("Click on reset password");
        return clickElement(resetPassword,10);
    }

    public boolean verifyResetMessageDisplay() {
        log.info("Verify reset password message display");
        return getElementText(loginMessage).contains("If we have your email address on file");
    }

    public boolean inputForgotPasswordEmail(String email) {
        log.info("Input forgot password email");
        return sendKeys(forgotPasswordEmail, email);
    }

    public boolean clickResetPasswordButton() {
        log.info("Click on reset password button");
        return clickElement(resetPasswordButton);
    }

    public boolean verifyLoginErrorMessageDisplay() {
        log.info("Verify login error message display");
        return getElementText(loginMessage).contains("Invalid user name or password");
    }

    public boolean verifyNotRobotTextDisplay() {
        log.info("Verify 'I am not a robot' text display");
        List<WebElement> frames = driver.findElements(By.tagName("iframe"));
        String winHanaleBefore = driver.getWindowHandle();
        driver.switchTo().frame(0);
        isElementDisplayed(notRobotRecaptcha);
        driver.switchTo().window(winHanaleBefore);
        return true;
    }


}
