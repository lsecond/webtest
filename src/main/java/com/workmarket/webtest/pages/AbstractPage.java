package com.workmarket.webtest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class AbstractPage {

    public static final int TIMEOUT_IN_SECONDS = 5;

    public static final Logger log = Logger.getLogger(Class.class.getName());
    protected static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public WebDriverWait getWait(int timeout) {
        final WebDriverWait waiting = new WebDriverWait(driver, timeout);
        waiting.ignoring(StaleElementReferenceException.class);
        return waiting;
    }

    public WebElement waitForElementVisibility(WebElement element, int timeOut) {
        if (element == null) {
            log.severe("Element is null");
            return null;
        }
        try {
            return getWait(timeOut).until(ExpectedConditions.visibilityOf(element));
        } catch (WebDriverException e) {
            log.info("Element is not visible");
            return null;
        }
    }

    public WebElement waitForElementVisibility(WebElement element) {
        return waitForElementVisibility(element, TIMEOUT_IN_SECONDS);
    }

    public boolean waitForElementToExist(WebElement element, int timeOut) {
        return waitForElementVisibility(element, timeOut) != null;
    }


    public boolean clickElement(WebElement element, int waitTime ) {
        if (!waitForElementToExist(element, waitTime)) {
            log.severe("Element is null!!");
            return false;
        }
        try {
            element.click();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            log.severe("Could not click Mobile Element!");
            return false;
        }
        return true;
    }

    public boolean clickElement(WebElement element) {
        return clickElement(element, TIMEOUT_IN_SECONDS);
    }

    public boolean sendKeys(WebElement element, String keysToSend) {
        try {
            log.info("Sending keys: " + keysToSend);
            element.sendKeys(keysToSend);
        } catch (WebDriverException e) {
            log.severe("Sending Keys failed!");
            return false;
        }
        return true;
    }

    public boolean isElementDisplayed(WebElement element) {
        if (element == null) {
            log.severe("Element is null");
            return false;
        }
        try {
            return element.isDisplayed();
        } catch (WebDriverException e) {
            log.severe("Element is not displayed!!");
            return false;
        }
    }




}
