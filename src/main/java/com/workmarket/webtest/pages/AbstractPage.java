package com.workmarket.webtest.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Logger;

public class AbstractPage {

    public static final int TIMEOUT_FIVE_SECONDS = 5;

    public static final Logger log = Logger.getLogger(Class.class.getName());
    protected static WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public static void sleep(int sleepMillis) {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            log.severe(e.toString());
        }
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
        return clickElement(element, TIMEOUT_FIVE_SECONDS);
    }

    public boolean sendKeys(WebElement element, String keysToSend) {
        if (!waitForElementToExist(element, TIMEOUT_FIVE_SECONDS)) {
            log.severe("Element is null!!");
            return false;
        }
        try {
            log.info("Sending keys: " + keysToSend);
            element.clear();
            element.sendKeys(keysToSend);
        } catch (WebDriverException e) {
            log.severe("Sending Keys failed!");
            return false;
        }
        return true;
    }

    public boolean sendKeys(WebElement element, Keys keysToSend) {
        if (!waitForElementToExist(element, TIMEOUT_FIVE_SECONDS)) {
            log.severe("Element is null!!");
            return false;
        }
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

    public boolean waitingFor(ExpectedCondition<Boolean> expectedConditions, WebDriverWait wait) {
        Boolean found;
        try {
            found = wait.until(expectedConditions);
        } catch (NoSuchElementException e) {
            log.severe("Element not found");
            found = false;
        } catch (TimeoutException e) {
            log.severe("Timed out while waiting for element");
            found = false;
        }
        return found;
    }

    public static ExpectedCondition<Boolean> loadListFinish(By by) {
        int size = driver.findElements(by).size();
        return new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
               int newSize =  driver.findElements(by).size();
               sleep(2000);
               if(newSize == size){
                   log.info("List load finished");
                   return true;
               } else {
                   log.info("List still loading");
                   return false ;
               }
            }
        };
    }

    public String getUrl() {
        log.info("Get current url");
        return driver.getCurrentUrl();
    }

    public String getElementText(WebElement element) {
        if (waitForElementVisibility(element, TIMEOUT_FIVE_SECONDS) == null) {
            log.severe("Element is null");
            return "";
        }
        String gotElementText;
        try {
            gotElementText = element.getText();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            log.severe("Element does not exist");
            gotElementText = null;
        } catch (NullPointerException e) {
            log.severe("Element is null");
            gotElementText = null;
        }
        if (gotElementText == null) {
            gotElementText = "";
        }
        return gotElementText;
    }




}
