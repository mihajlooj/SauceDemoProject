package Tests;

import Base.BaseTest;
import Base.ExcelReader;
import Pages.HomePage;
import Pages.LogInPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class LockedOutUserLogInFunctionality extends BaseTest {

    @BeforeMethod
    public void ApplicationSetUp() throws IOException, InterruptedException {
        drajver.navigate().to("https://www.saucedemo.com/");
        logInPage = new LogInPage();
        homePage = new HomePage();
        UserBase = new ExcelReader("Users\\Swag Labs Users.xlsx");
        wait = new WebDriverWait(drajver, Duration.ofSeconds(7));
    }

    @Test
    public void LockedUserCannotLogIn() {
        logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 2, 2));
        logInPage.PasswordFieldInput(UserBase.getStringData("Credentials", 1, 1));
        logInPage.clickOnLoginButton();
        Assert.assertEquals(logInPage.ErrorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void LockedUserCannotLogInWithInvalidPassword() {
        logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 2, 2));
        logInPage.PasswordFieldInput(UserBase.getStringData("Credentials", 1, 3));
        logInPage.clickOnLoginButton();
        Assert.assertEquals(logInPage.ErrorMessage.getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void LockedUserCannotLogInWithBlankPasswordField() {
        logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 2, 2));
        logInPage.PasswordFieldInput("");
        logInPage.clickOnLoginButton();
        Assert.assertEquals(logInPage.ErrorMessage.getText(), "Epic sadface: Password is required");
    }

    @Test
    public void LockedUserCannotLogInWithSpammingLogInButton() {
        logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 2, 2));
        logInPage.PasswordFieldInput(UserBase.getStringData("Credentials", 1, 1));
        for (int i = 0; i <= 10; i++) {
            logInPage.clickOnLoginButton();

        }
        Assert.assertEquals(logInPage.ErrorMessage.getText(), "Epic sadface: Sorry, this user has been locked out.");
    }

    @Test
    public void LockedUserCannotLogInWithClickSpammingLogInButtonWithBlankPasswordField() {
        logInPage.UsernameFieldInput(UserBase.getStringData("Credentials", 2, 2));
        logInPage.PasswordFieldInput("");
        for (int i = 0; i <= 10; i++) {
            logInPage.clickOnLoginButton();
        }
        Assert.assertEquals(logInPage.ErrorMessage.getText(), "Epic sadface: Password is required");
    }
}


