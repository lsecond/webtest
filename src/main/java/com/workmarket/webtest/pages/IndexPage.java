package com.workmarket.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class IndexPage extends AbstractPage{

    @FindBy(id = "navMainLinks")
    private WebElement mainNavigateBar;

    public IndexPage(WebDriver driver) {
        super(driver);
    }

    public boolean verifyMainNavDisplayed() {
        log.info("Verify main navigate bar displayed");
        return waitForElementToExist(mainNavigateBar,TIMEOUT_FIVE_SECONDS * 2);
    }
}
