package com.vmo.demowebshop.interfaces;

import static com.vmo.demowebshop.common.Locator.xpath;

public class LoginPageUI {
    public static final String EMAIL_TEXT_BOX = xpath("//input[@id='Email']");
    public static final String PASSWORD_TEXT_BOX = xpath("//input[@id='Password']");
    public static final String LOGIN_BTN = xpath("//input[contains(@class, 'login-button')]");
    public static final String VALIDATE_ERROR_MSG = xpath("//div[@class='validation-summary-errors']//li | //span[@class='field-validation-error']");
    public static final String CUSTOMER_INFO = xpath("//div[@class='header-links']//a[@class='account']");
}
