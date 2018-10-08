package com.workmarket.webtest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends AbstractPage{

    @FindBy(id = "home__Find Talent-button")
    private WebElement findTalentButton;

    @FindBy(xpath = "/html/body/div[12]/div/div/div/div/div/div[6]/a/div/div/div")
    WebElement signOut;

    @FindBy(xpath = "//*[@id='wm-main-nav']/div/div[2]/div[2]/div/div/div[4]/button")
    WebElement settingButton;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public boolean clickFindTalent() {
        log.info("Click on 'Find Talent' button");
        return clickElement(findTalentButton);
    }

    public boolean clickOnUserSettingButton(){
        log.info("Click on user setting button");
        return clickElement(settingButton);
    }

    public boolean clickOnSignOutButton(){
        log.info("Click on sign out button");
        sleep(1000);
        return clickElement(signOut);
    }
}
