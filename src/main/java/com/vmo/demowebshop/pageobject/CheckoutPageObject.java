package com.vmo.demowebshop.pageobject;

import com.vmo.demowebshop.common.BasePage;
import com.vmo.demowebshop.interfaces.CheckoutPageUI;
import com.vmo.demowebshop.interfaces.CommonPageUI;
import com.vmo.demowebshop.storedata.AddToCartData;
import com.vmo.demowebshop.utils.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class CheckoutPageObject extends BasePage {
    private WebDriver driver;
    private String firstName, lastName, email, country, city, address1, zipcode, phoneNumber;

    private String paymentMethod;
    public CheckoutPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public CheckoutPageObject verifyInCheckoutPage() {
        Log.allure("Verify: User In Checkout page");
        Assert.assertTrue(getWebElement(driver, CheckoutPageUI.CHECKOUT_AS_GUEST_TITLE).isDisplayed());

        return this;
    }

    public CheckoutPageObject clickCheckoutAsGuestButton() {
        Log.allure("Step: Click Checkout As Guest button");
        clickToElement(driver, CommonPageUI.DYNAMIC_INPUT_BTN, "Checkout as Guest");

        return new CheckoutPageObject(driver);
    }

    public CheckoutPageObject verifyStepIsExpended(String step) {
        Log.allure("Verify: %s is expanded", step);
        waitForAllElementsInvisible(driver, CheckoutPageUI.WAITING_TEXT);
        waitForElementVisible(driver, CheckoutPageUI.DYNAMIC_STEP_CONTENT, step);
        String attributeStyle = getAttributeElement(driver, CheckoutPageUI.DYNAMIC_STEP_CONTENT, "style", step);
        softAssert.assertFalse(attributeStyle.contains("display: none"));

        return this;
    }

    public CheckoutPageObject fillInformationBillingAddress(String firstname, String lastname, String email, String country,
    String city, String address1, String zipCode, String phoneNumber) {

        Log.allure("Step: fill information in Billing Address");
        this.firstName = firstname;
        this.lastName = lastname;
        this.email = email;
        this.country = country;
        this.city = city;
        this.address1 = address1;
        this.zipcode = zipCode;
        this.phoneNumber = phoneNumber;

        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, firstname, "First name");
        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, lastname, "Last name");
        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, email, "Email");

        selectItemInDefaultDropdownByText(driver, CheckoutPageUI.COUNTRY_DROPDOWN, country);

        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, city, "City");
        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, address1, "Address 1");
        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, zipCode, "Zip / postal code");
        sendKeysToElement(driver, CheckoutPageUI.DYNAMIC_TEXT_BOX, phoneNumber, "Phone number");

        clickToElement(driver, CheckoutPageUI.DYNAMIC_CHECKOUT_BTN_ON_STEP, "Billing address");
        return this;
    }

    public CheckoutPageObject verifyPaymentMethodSelected(String method) {
        Log.allure("Verify: Payment method selected is %s", method);
        paymentMethod = method;

        softAssert.assertTrue(isElementSelected(driver, CheckoutPageUI.PAYMENT_METHOD_RADIO, method));
        return this;
    }

    public CheckoutPageObject clickContinueBtnOnStep(String step) {
        Log.allure("Step: Click Continue button on %s", step);
        waitForElementClickable(driver, CheckoutPageUI.DYNAMIC_CHECKOUT_BTN_ON_STEP, step);
        clickToElement(driver, CheckoutPageUI.DYNAMIC_CHECKOUT_BTN_ON_STEP, step);

        return this;
    }

    public CheckoutPageObject verifyPaymentInfoIs(String text) {
        Log.allure("Verify: Payment info is %s", text);
        waitForElementVisible(driver, CheckoutPageUI.PAYMENT_INFORMATION_TXT);
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.PAYMENT_INFORMATION_TXT), text);

        return this;
    }

    public CheckoutPageObject verifyInformationFilled() {
        Log.allure("Verify: Information filled");
        //verify billing address
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "name").trim(),
                String.join(" ", firstName, lastName));
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "email").trim(),
                String.join(" ", "Email:", email));
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "phone").trim(),
                String.join(" ", "Phone:", phoneNumber));
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "address1").trim(), address1);
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "city-state-zip").trim(),
                String.join(" , ", city, zipcode));
        softAssert.assertEquals(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "country").trim(), country);

        //verify payment method
        softAssert.assertTrue(getTextElement(driver, CheckoutPageUI.DYNAMIC_BILLING_INFO, "payment-method").trim().contains(paymentMethod));

        //Verify products
        List<WebElement> products = getListWebElements(driver, CommonPageUI.PRODUCT_NAME);
        List<String> productsName = new ArrayList<>();
        for (WebElement element : products) {
            productsName.add(getTextElement(element));
        }

        softAssert.assertEquals(productsName, AddToCartData.getProductsList());

        //verify total
        double subTotal = Double.parseDouble(getTextElement(driver, CheckoutPageUI.DYNAMIC_CART_TOTAL, "Sub-Total:"));
        double fee = Double.parseDouble(getTextElement(driver, CheckoutPageUI.DYNAMIC_CART_TOTAL, "Payment method additional fee:"));
        double total = Double.parseDouble(getTextElement(driver, CheckoutPageUI.ORDER_TOTAL));
        softAssert.assertEquals(total, subTotal + fee);

        return this;
    }

    public CheckoutCompletedPageObject clickConfirmBtn() {
        Log.allure("Step: Click Confirm button");
        clickToElement(driver, CommonPageUI.DYNAMIC_INPUT_BTN, "Confirm");
        waitForElementInvisible(driver, CommonPageUI.DYNAMIC_INPUT_BTN, "Confirm");

        return new CheckoutCompletedPageObject(driver);
    }

}
