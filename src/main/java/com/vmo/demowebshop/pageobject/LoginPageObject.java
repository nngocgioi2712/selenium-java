package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.LoginPageUI;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageObject verifyInLoginPage() {
        Log.allure("Verify: User in Login page");
        Assert.assertEquals(getTitle(driver), "Demo Web Shop. Login");

        return this;
    }

    public LoginPageObject fillLoginInformation(String email, String password) {
        Log.allure("Step: Fill login information");
        sendKeysToElement(driver, LoginPageUI.EMAIL_TEXT_BOX, email);
        sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXT_BOX, password);

        return this;
    }

    public LoginPageObject submitLogin() {
        Log.allure("Step: Submit login");
        clickToElement(driver, LoginPageUI.LOGIN_BTN);

        return this;
    }

    public LoginPageObject verifyValidateMsg(String msg) {
        if(msg.isEmpty()) {
            waitForElementVisible(driver, LoginPageUI.CUSTOMER_INFO);
            verifyUserLoggedIn();
        } else {
            Log.allure("Verify: Message is displayed '%s'", msg);
            Assert.assertEquals(getTextElement(driver, LoginPageUI.VALIDATE_ERROR_MSG), msg);
        }

        return this;
    }

    public void verifyUserLoggedIn() {
        Log.allure("Verify: User logged in");
        Assert.assertTrue(getWebElement(driver, LoginPageUI.CUSTOMER_INFO).isDisplayed());
    }
}
