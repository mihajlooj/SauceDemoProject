package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.LogInPage;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;


public class LogInFunctionalityTest extends BaseTest {

    private static final Logger log = LoggerFactory.getLogger(LogInFunctionalityTest.class);

    @BeforeMethod
    public void ApplicationSetUp() throws IOException, InterruptedException {
        drajver.navigate().to("https://www.saucedemo.com/");
        logInPage = new LogInPage();
        homePage = new HomePage();
        UserBase = new ExcelReader("src\\test\\resources\\testdata\\Swag Labs Users.xlsx");
        wait = new WebDriverWait(drajver, Duration.ofSeconds(7));
    }

    @Test
    public void UserCanLogInWithValidUsernameAndValidPassword() throws InterruptedException {
for(int i=0; i< UserBase.getLastRow("Credentials"); i++){
    logInPage.UsernameFieldInput(UserBase.getStringData("Credentials",i+1,0));
    logInPage.PasswordFieldInput(UserBase.getStringData("Credentials",1,1));
    logInPage.clickOnLoginButton();
    Assert.assertTrue(homePage.ShoppingCartButton.isDisplayed());
    Thread.sleep(2500);
    homePage.clickOnMenuButton();
    Thread.sleep(1000);
    homePage.clickOnLogOutButton();
    Assert.assertTrue(logInPage.Loginbutton.isDisplayed());
}
    }

    @Test
    public void UserCannotLogInWithBlankUsernameAndPasswordFields(){
        logInPage.clickOnLoginButton();
        Assert.assertTrue(logInPage.Loginbutton.isDisplayed());
    }

    @Test
    public void UserCannotLogInWithBlankUsernameField() throws IOException {
        logInPage.UsernameFieldInput("");
        logInPage.PasswordFieldInput(UserBase.getStringData("Credentials",1,2));
        logInPage.clickOnLoginButton();
        String expectedMessage = "Epic sadface: Username is required";
        String actualMessage = logInPage.ErrorMessage.getText();
        Assert.assertEquals(actualMessage, expectedMessage);
    }

    @Test
    public void UserCannotLogInWithBlankPasswordField() throws InterruptedException {
        for(int i=0; i<UserBase.getLastRow("Credentials"); i++){
            logInPage.UsernameFieldInput(UserBase.getStringData("Credentials",i+1,0));
            logInPage.clickOnLoginButton();
            Assert.assertTrue(logInPage.Loginbutton.isDisplayed());
            Assert.assertEquals(logInPage.ErrorMessage.getText(), "Epic sadface: Password is required");
        }
    }

    @AfterClass
    public void conclusion() throws InterruptedException {
        Thread.sleep(10000);
        drajver.quit();

    }
}
