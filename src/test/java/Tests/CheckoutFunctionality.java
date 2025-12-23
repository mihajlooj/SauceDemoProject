package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LogInPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class CheckoutFunctionality extends BaseTest {

    @BeforeMethod
    public void pageSetUp() throws IOException {
        drajver.navigate().to("https://www.saucedemo.com/");
        logInPage = new LogInPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        checkoutPage = new CheckoutPage();
        UserBase = new ExcelReader("Users\\Swag Labs Users.xlsx");
        logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 1, 0));
        logInPage.PasswordFieldInput(UserBase.getStringData("Credentials", 1, 1));
        logInPage.clickOnLoginButton();
    }

    @AfterMethod
    public void nextTestSetup() throws InterruptedException {
        homePage.clickOnMenuButton();
        Thread.sleep(1500);
        homePage.clickOnResetButton();
        drajver.navigate().refresh();
    }

    @Test
    public void userCanCheckoutWithProperlyFilledForm() throws InterruptedException {
        homePage.clickOnAddToCartButton();
        homePage.clickOnShoppingCartButton();
        cartPage.clickOnCheckoutButton();
        checkoutPage.FillCheckoutForm(UserBase.getStringData("User Info",1,0), UserBase.getStringData("User Info", 1, 1), UserBase.getStringData("User Info",1,2) );
        checkoutPage.clickOnContinueButton();
        checkoutPage.clickOnFinishButton();
        Assert.assertEquals(checkoutPage.checkoutMessage.getText(),"Thank you for your order!");
    }

    @Test
    public void userCannotCheckoutWithoutFirstName(){
        homePage.clickOnAddToCartButton();
        homePage.clickOnShoppingCartButton();
        cartPage.clickOnCheckoutButton();
        checkoutPage.FillCheckoutForm("", UserBase.getStringData("User Info", 1, 1), UserBase.getStringData("User Info",1,2) );
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(checkoutPage.errorMessage.getText(), "Error: First Name is required");
    }

    @Test
    public void userCannotCheckoutWithoutLastName(){
        homePage.clickOnAddToCartButton();
        homePage.clickOnShoppingCartButton();
        cartPage.clickOnCheckoutButton();
        checkoutPage.FillCheckoutForm(UserBase.getStringData("User Info",1,0), "", UserBase.getStringData("User Info",1,2) );
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(checkoutPage.errorMessage.getText(), "Error: Last Name is required");
    }

    @Test
    public void userCannotCheckoutWithoutZipCode(){
        homePage.clickOnAddToCartButton();
        homePage.clickOnShoppingCartButton();
        cartPage.clickOnCheckoutButton();
        checkoutPage.FillCheckoutForm(UserBase.getStringData("User Info",1,0), UserBase.getStringData("User Info", 1, 1), "");
        checkoutPage.clickOnContinueButton();
        Assert.assertEquals(checkoutPage.errorMessage.getText(), "Error: Postal Code is required");
    }

    @AfterClass
    public void conclusion() throws InterruptedException {
        Thread.sleep(5000);
        drajver.quit();
    }
}
