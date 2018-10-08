package com.workmarket.webtest.tests;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class MainPageSearchTest extends AbstractTest{

    final String searchText = "test";

    @BeforeClass
    public static void beforeClassMainPageSearch(){
        PublicAction.login(UserEnum.VALID_USER);
    }

    @Test
    public void searchForTalent() {
        Assert.assertTrue("Unable to click on 'Find Talent' button",  getWeb().MainPage().clickFindTalent());
        Assert.assertTrue(String.format("Unable to input search text '%s'", searchText),  getWeb().FindTalentPage().inputSearchText(searchText));
        Assert.assertTrue(String.format("Unable to load all result"),  getWeb().FindTalentPage().waitForSearchResultLoadCompletely());
        Assert.assertTrue(String.format("Find result has no text '%s'", searchText),   getWeb().FindTalentPage().verifyAllResult(searchText));
    }
}
