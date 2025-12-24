package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LogInPage;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class CartFunctionalityTest extends BaseTest {



    @BeforeMethod
    public void pageSetUp() throws IOException {
        drajver.navigate().to("https://www.saucedemo.com/");
        logInPage = new LogInPage();
        homePage = new HomePage();
        cartPage = new CartPage();
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

    @Test(priority = 1)
    public void UserCanAddItemInCart(){
homePage.clickOnAddToCartButton();
        Assert.assertTrue(homePage.ShoppingCartItemCounter.isDisplayed());
    }

    @Test(priority = 2)
    public void UserCanRemoveItemFromCart() throws InterruptedException {
        homePage.clickOnAddToCartButton();
        Assert.assertTrue(homePage.ShoppingCartItemCounter.isDisplayed());
        homePage.clickOnShoppingCartButton();
        Thread.sleep(1500);
        cartPage.clickOnRemoveItem();
        cartPage.clickOnMenuButton();
        Thread.sleep(1000);
        cartPage.clickOnAllItemsButton();
    }

    @Test(priority = 3)
    public void UserCanAddMultipleItemsInCart() throws InterruptedException {

        homePage.AddAllItemsInCart();
        String expectedQuaintity = "6";
        Assert.assertEquals(homePage.ShoppingCartItemCounter.getText(), expectedQuaintity);
    }

    @Test(priority = 4)
    public void UserCanRemoveAllItemsFromCart() throws InterruptedException {
        homePage.AddAllItemsInCart();
       homePage.clickOnShoppingCartButton();
        cartPage.RemoveAllItems();
        boolean isNotPresent;
        try {
            cartPage.RemoveItem.isDisplayed();
            isNotPresent = false;
        } catch (NoSuchElementException e) {
            isNotPresent = true;
        }
        assertTrue("Element should not be present", isNotPresent);
    }

    @AfterClass
    public void conclusion() throws InterruptedException {
        Thread.sleep(5000);
        drajver.quit();
    }
}
