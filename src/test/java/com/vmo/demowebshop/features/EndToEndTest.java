package com.vmo.demowebshop.features;

import com.vmo.demowebshop.common.BaseTest;
import com.vmo.demowebshop.pageobject.HomePageObject;
import com.vmo.demowebshop.utils.DataUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

@Epic("DemoWebShop")
@Feature("E2E")
public class EndToEndTest extends BaseTest {

    private String firstName, lastName, email, country, city, address1, zipcode, phoneNumber;

    @BeforeMethod
    public void setUpData() {
        firstName = DataUtil.getData().getFirstName();
        lastName = DataUtil.getData().getLastName();
        email = DataUtil.getData().getEmailAddress();
        country = DataUtil.getData().getCountry();
        city = DataUtil.getData().getCityName();
        address1 = DataUtil.getData().getFullAddress();
        zipcode = DataUtil.getData().getZipCode();
        phoneNumber = DataUtil.getData().getPhoneNumber();
    }

    @Test
    @Story("Buy item successfully")
    public void TC_04_buyItemSuccessfully() {
        HomePageObject homePageObject = new HomePageObject(driver);

        homePageObject.verifyInHomepage()
                .clickDigitalDownloadsMenu()
                .verifyInDigitalDownloadsPage()
                .clickAddToCartInAProduct()
                .verifyMessageIsDisplay("The product has been added to your shopping cart")
                .verifyTotalItemsInShoppingCartIncreases(1)
                .clickShoppingCartTag()
                .verifyInCartPage()
                .verifySumOfTotalEqualToSubTotal()
                .checkToTermOfService()
                .verifyCheckBoxIsChecked()
                .clickCheckoutButton()
                .verifyInCheckoutPage()
                .clickCheckoutAsGuestButton()
                .verifyStepIsExpended("Billing address")
                .fillInformationBillingAddress(firstName, lastName, email, "Viet Nam", city, address1, zipcode, phoneNumber)
                .verifyStepIsExpended("Payment method")
                .verifyPaymentMethodSelected("Cash On Delivery")
                .clickContinueBtnOnStep("Payment method")
                .verifyPaymentInfoIs("You will pay by COD")
                .clickContinueBtnOnStep("Payment information")
                .verifyStepIsExpended("Confirm order")
                .verifyInformationFilled()
                .clickConfirmBtn()
                .verifyInCheckoutCompletedPage()
                .clickContinueBtn()
                .verifyInHomepage();
    }
}
