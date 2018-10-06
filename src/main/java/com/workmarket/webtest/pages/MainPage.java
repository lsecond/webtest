package com.workmarket.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage{

    @FindBy(id = "home__Find Talent-button")
    private WebElement findTalentButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickFindTalent() {
        log.info("Click on 'Find Talent' button");
        return clickElement(findTalentButton);
    }

    public boolean verifyFindTalentButtonDisplay(){
        log.info("Verify find talent button displayed");
        return waitForElementToExist(findTalentButton, TIMEOUT_IN_SECONDS);
    }
}
