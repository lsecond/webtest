package com.workmarket.webtest.tests;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static com.workmarket.webtest.tests.AbstractTest.UserEnum.EMPTY_EMIAL_USER;
import static com.workmarket.webtest.tests.AbstractTest.UserEnum.EMPTY_PASSWORD_USER;
import static com.workmarket.webtest.tests.AbstractTest.UserEnum.WRONG_USER;

@RunWith(Parameterized.class)
public class InvalidLoginTest extends AbstractTest {

    @Rule
    public ExternalResource resource = new ExternalResource() {
        @Override
        protected void before() throws Throwable {
            if(!getWeb().LoginPage().getUrl().contains("login")){
                driver.get(loginUrl);
            }
        }
    };


    private UserEnum userEnum;

    public InvalidLoginTest(UserEnum userEnum) {
        this.userEnum = userEnum;

    }

    @Parameterized.Parameters(name = "{index}:{0}")
    public static Collection<Object[]> users() {
        final ArrayList<UserEnum> params = new ArrayList<>(Arrays.asList(
                WRONG_USER,
                EMPTY_EMIAL_USER,
                EMPTY_PASSWORD_USER));
        return parametersInjection(params.toArray());
    }

    @Test
    public void inValidUserPasswordLogin() {
        PublicAction.login(userEnum);
        Assert.assertTrue("Unable to verify error message display",  getWeb().LoginPage().verifyLoginErrorMessageDisplay());
        Assert.assertTrue("Unable to verify not robot check text display ",  getWeb().LoginPage().verifyNotRobotTextDisplay());
    }
}
