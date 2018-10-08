package com.workmarket.webtest.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Locale;

public class FindTalentPage extends AbstractPage{

    @FindBy(id = "input-text")
    private WebElement searchField;

    @FindBy(id = "page_size")
    private WebElement pageSize;

    public FindTalentPage(WebDriver driver) {
        super(driver);
    }

    public boolean inputSearchText(String searchText) {
        log.info(String.format("Input %s in search field and then hit the ENTER KEY",searchText));
        return  sendKeys(searchField, searchText) && sendKeys(searchField, Keys.ENTER);
    }

    public boolean waitForSearchResultLoadCompletely(){
        log.info("Wait for all result load completely");//*[@id="page_size"]
        final By by = By.className("profile-card--photo");
        return waitingFor(loadListFinish(by), getWait(30));
    }

    private int getPageRecordLimit(){
        Select pageMaxResultNumber = new Select(pageSize);
        final String maxResultNumber =
                pageMaxResultNumber.getFirstSelectedOption().getText();
        return Integer.parseInt(maxResultNumber.split(" ")[0]);
    }

    public boolean verifyAllResult(String searchText) {
        log.info(String.format("Verify every result includes at least one %s text in details",searchText));
        List<WebElement> searchResultCell = driver.findElements(By.xpath( "//div[@id='search_results']//div[@class ='profile-card--details']"));
        final int size = searchResultCell.size();
        if(size < 1){
            log.severe("Unable find any record");
            return false;
        }
        if(size > getPageRecordLimit()){
            searchResultCell.subList(50,size).clear();
        }
        for(WebElement detail : searchResultCell){
            List<WebElement> children = detail.findElements(By.xpath(".//*"));
            WebElement matchupElement = children.stream().filter(child -> child.getText()!= null &&
                    !child.getText().isEmpty() &&
                    child.getText().toLowerCase(Locale.CANADA).contains(searchText.toLowerCase())).findFirst().get();
            log.info(String.format("find match text %s on a element %s ", matchupElement.getText(), matchupElement.getAttribute("class")));
            if(matchupElement==null) {
                log.severe(String.format(String.format("find one record has no text %s", searchText)));
                return false;
            }
        }
        return true;
    }
}
