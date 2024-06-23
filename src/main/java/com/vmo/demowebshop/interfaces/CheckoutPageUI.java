package com.vmo.demowebshop.interfaces;

import static com.vmo.demowebshop.common.Locator.xpath;

public class CheckoutPageUI {

    public static final String CHECKOUT_AS_GUEST_TITLE = xpath("//strong[text()='Checkout as a guest or register']");
    public static final String DYNAMIC_STEP_CONTENT = xpath("//h2[text()='%s']/../following-sibling::div");
    public static final String DYNAMIC_TEXT_BOX = xpath("//label[contains(text(), '%s')]/following-sibling::input");
    public static final String COUNTRY_DROPDOWN = xpath("//select[@id='BillingNewAddress_CountryId']");
    public static final String WAITING_TEXT = xpath("//span[@id='shipping-please-wait']");
    public static final String PAYMENT_METHOD_RADIO = xpath("//label[contains(text(), 'Cash')]/preceding-sibling::input");
    public static final String PAYMENT_INFORMATION_TXT = xpath("//div[@class='section payment-info']//p");
    public static final String DYNAMIC_CHECKOUT_BTN_ON_STEP = xpath("//h2[text()='%s']/ancestor::li//input[@value='Continue']");
    public static final String DYNAMIC_BILLING_INFO = xpath("//ul[@class='billing-info']/li[@class='%s']");
    public static final String DYNAMIC_CART_TOTAL = xpath("//span[text()='%s']/../following-sibling::td");
    public static final String ORDER_TOTAL = xpath("//span[contains(@class, 'order-total')]");
}
