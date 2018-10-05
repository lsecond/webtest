package com.workmarket.webtest.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage {

    @FindBy(id = "home__Find Talent-button")
    private WebElement findTalentButton;

    public void clickFindTalent() {
        findTalentButton.click();
    }
}
