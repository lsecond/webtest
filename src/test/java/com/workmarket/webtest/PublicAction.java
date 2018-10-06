package com.workmarket.webtest;

import org.junit.Assert;

import static com.workmarket.webtest.AbstractTest.getWeb;

public class PublicAction {

    private PublicAction() {}

    static public void login(String userEmail, String password) {
        Assert.assertTrue("Unable to input email",  getWeb().LoginPage().inputEmail(userEmail));
        Assert.assertTrue("Unable to input password",  getWeb().LoginPage().inputPassword(password));
        Assert.assertTrue("Unable to click on login button",  getWeb().LoginPage().clickLogin());
    }
}
