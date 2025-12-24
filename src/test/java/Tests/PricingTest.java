package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LogInPage;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class PricingTest extends BaseTest {

    @BeforeMethod
public void pageSetUp() throws IOException {
    drajver.navigate().to("https://www.saucedemo.com/");
    logInPage = new LogInPage();
    homePage = new HomePage();
    cartPage = new CartPage();
    checkoutPage = new CheckoutPage();
    UserBase = new ExcelReader("src\\test\\resources\\testdata\\Swag Labs Users.xlsx");
    logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 1, 0));
    logInPage.PasswordFieldInput(UserBase.getStringData("Credentials", 1, 1));
    logInPage.clickOnLoginButton();
}

    @AfterMethod
    public void nextTestSetup() throws InterruptedException {
        homePage.clickOnMenuButton();
        Thread.sleep(2000);
        homePage.clickOnResetButton();
        drajver.navigate().refresh();
    }

    @Test(priority = 0)
    public void PriceOfItemIsTheSameInCartPage(){
        homePage.clickOnAddToCartButton();
        double itemPrice = homePage.getItemPrice();
        homePage.clickOnShoppingCartButton();
        Assert.assertEquals(cartPage.getItemPrice(), itemPrice);
    }

        @Test(priority = 1)
        public void PriceOfMultipleAddedItemsIsTheSameOnCartPage(){
            double itemPrice = 0.00;
        homePage.addEveryItemAndCheckItemPrice();
            for (WebElement item : homePage.listOfItems) {
                 itemPrice = homePage.getItemPrice();
            }
            homePage.clickOnShoppingCartButton();
            Assert.assertEquals(cartPage.checkPrice(), itemPrice);
        }



}
