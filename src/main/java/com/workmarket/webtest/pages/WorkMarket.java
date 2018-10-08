package com.workmarket.webtest.pages;

import org.openqa.selenium.WebDriver;

public class WorkMarket {

    private final WebDriver driver;

    public WorkMarket(WebDriver driver){
        this.driver=driver;
    }

    public FindTalentPage FindTalentPage() {return new FindTalentPage(driver);}

    public MainPage MainPage() {return new MainPage(driver);}

    public LoginPage LoginPage() {return new LoginPage(driver);}

    public IndexPage IndexPage() {return new IndexPage(driver);}
}
