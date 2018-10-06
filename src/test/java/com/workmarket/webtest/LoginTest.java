package com.workmarket.webtest;

import org.junit.Assert;
import org.junit.Test;

public class LoginTest extends AbstractTest {

    @Test
    public void normalLogin() {
        PublicAction.login(USER_EMAIL, USER_PASSWORD);
        Assert.assertTrue("Unable to verify login successfully",  getWeb().MainPage().verifyFindTalentButtonDisplay());
    }
}
