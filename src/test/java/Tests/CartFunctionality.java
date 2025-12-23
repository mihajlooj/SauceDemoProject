package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.CartPage;
import Pages.HomePage;
import Pages.LogInPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertTrue;

public class CartFunctionality extends BaseTest {



    @BeforeMethod
    public void pageSetUp() throws IOException {
        drajver.navigate().to("https://www.saucedemo.com/");
        logInPage = new LogInPage();
        homePage = new HomePage();
        cartPage = new CartPage();
        UserBase = new ExcelReader("Users\\Swag Labs Users.xlsx");
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
    public void UserCanAddItemInCart(){
homePage.clickOnAddToCartButton();
        Assert.assertTrue(homePage.ShoppingCartItemCounter.isDisplayed());
        //drajver.manage().deleteAllCookies();
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
       /*
        int counter=0;
        for(int i=0; i<homePage.listOfItems.size(); i++){
            homePage.clickOnAddToCartButton();
            counter++;
            Thread.sleep(500);
 }
    */
        homePage.AddAllItemsInCart();
        String expectedQuaintity = "6";
        Assert.assertEquals(homePage.ShoppingCartItemCounter.getText(), expectedQuaintity);
    }

    @Test(priority = 4)
    public void UserCanRemoveAllItemsFromCart() throws InterruptedException {
        /*
        int counter = 0;
       for(int i=0; i<homePage.listOfItems.size(); i++){
            homePage.clickOnAddToCartButton();
            counter++;
            Thread.sleep(500);
        }

         */
        homePage.AddAllItemsInCart();
       homePage.clickOnShoppingCartButton();
        /*
       for(int a=0; a<counter; a++){
            cartPage.clickOnRemoveItem();
        }
         */
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
