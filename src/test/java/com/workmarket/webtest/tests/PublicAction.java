package com.workmarket.webtest.tests;

import org.junit.Assert;

import static com.workmarket.webtest.tests.AbstractTest.getWeb;

public class PublicAction {

    private PublicAction() {}

    static public void login(AbstractTest.UserEnum user) {
        Assert.assertTrue("Unable to input email",  getWeb().LoginPage().inputEmail(user.email));
        Assert.assertTrue("Unable to input password",  getWeb().LoginPage().inputPassword(user.password));
        Assert.assertTrue("Unable to click on login button",  getWeb().LoginPage().clickLogin());
    }
}
