package com.workmarket.webtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class FindTalentPage extends AbstractPage{

    @FindBy(id = "input-text")
    private WebElement searchField;

    @FindBy(id = "//*[@class='profile-card--details']")
    private List<WebElement> searchResultCell;

    public FindTalentPage(WebDriver driver) {
        super(driver);
    }

    public boolean inputSearchText(String searchText) {
       return  sendKeys(searchField, searchText);
      //  searchField.sendKeys(Keys.ENTER);
    }

    public boolean verifyAllResult(String searchText) {
        for(WebElement detail : searchResultCell){
            List<WebElement> childs = detail.findElements(By.xpath(".//*"));
            List<WebElement> matchupElements = childs.stream().filter(child -> child.getText()!= null &&
                    !child.getText().isEmpty() &&
                    child.getText().toLowerCase(Locale.CANADA).contains(searchText.toLowerCase())).collect(Collectors.toList());
            if(matchupElements.size() < 1) {
                log.severe(String.format(String.format("find one record has no text %s", searchText)));
                return false;
            }
        }
        return true;
    }
}
