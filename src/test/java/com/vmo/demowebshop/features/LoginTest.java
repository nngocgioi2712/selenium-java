package com.vmo.demowebshop.features;

import com.vmo.demowebshop.common.BaseTest;
import com.vmo.demowebshop.pageobject.HomePageObject;
import com.vmo.demowebshop.utils.ExcelUtil;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;

@Epic("DemoWebShop")
@Feature("Login")
public class LoginTest extends BaseTest {

    @DataProvider(name = "login_data")
    public Object[][] loginData() {
        String excelPath = String.join(File.separator, "src", "test", "resources", "dataTest", "AssignmentData.xlsx");
        ExcelUtil excelUtil = new ExcelUtil(excelPath, "Login");

        return excelUtil.getData();
    }

    @Test (dataProvider = "login_data")
    @Story("Login Validation")
    public void TC_01_verifyLoginValidation(String no, String username, String password, String expectedMessage) {
        HomePageObject homePageObject = new HomePageObject(driver);

        homePageObject.verifyInHomepage()
                .clickLoginTag()
                .verifyInLoginPage()
                .fillLoginInformation(username, password)
                .submitLogin()
                .verifyValidateMsg(expectedMessage);
    }

}
