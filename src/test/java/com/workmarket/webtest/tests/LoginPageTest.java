package com.workmarket.webtest.tests;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static com.workmarket.webtest.tests.AbstractTest.UserEnum.VALID_USER;

public class LoginPageTest extends AbstractTest {
    final String mainPageUrl = "workmarket.com/home";

    @Test
    public void validLoginAndLogOutTest() {
        PublicAction.login(VALID_USER);
        Assert.assertTrue("Unable to verify login successfully",  getWeb().MainPage().getUrl().contains(mainPageUrl));
        Assert.assertTrue("Unable to click on setting button",  getWeb().MainPage().clickOnUserSettingButton());
        Assert.assertTrue("Unable to click on sign out button",  getWeb().MainPage().clickOnSignOutButton());
        Assert.assertTrue("Unable to verify sign out successfully",  getWeb().IndexPage().verifyMainNavDisplayed());
    }

    @Test
    public void forgotPasswordTest() {
        Assert.assertTrue("Unable to click on 'forgot my password'", getWeb().LoginPage().clickResetPassword());
        Assert.assertTrue("Unable to input email for reset password", getWeb().LoginPage().inputForgotPasswordEmail("forgot" + VALID_USER.email));
        Assert.assertTrue("Unable to verify sign out successfully", getWeb().LoginPage().clickResetPasswordButton());
        Assert.assertTrue("Unable to verify reset message displayed", getWeb().LoginPage().verifyResetMessageDisplay());
    }

    @Before
    public void beforeValidLoginTest(){
        if(!getWeb().LoginPage().getUrl().contains("login") || getWeb().LoginPage().getUrl().contains("reset")){
            driver.get(loginUrl);
        }
    }
}
