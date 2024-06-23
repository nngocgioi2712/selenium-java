package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class CheckoutCompletedPageObject extends BasePage {
    private WebDriver driver;

    public CheckoutCompletedPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutCompletedPageObject verifyInCheckoutCompletedPage() {
        Assert.assertTrue(getCurrentUrl(driver).contains("/checkout/completed"));

        return this;
    }

    public HomePageObject clickContinueBtn() {
        clickToElement(driver, CommonPageUI.DYNAMIC_INPUT_BTN, "Continue");

        return new HomePageObject(driver);
    }
}
