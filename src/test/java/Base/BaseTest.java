package Base;

import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.HomePage;
import Pages.LogInPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {

    public static WebDriver drajver;
    public LogInPage logInPage;
    public HomePage homePage;
    public CartPage cartPage;
    public CheckoutPage checkoutPage;
    public ExcelReader UserBase;
    public WebDriverWait wait;

    @BeforeClass
    public void setUp() throws IOException {
        WebDriverManager.firefoxdriver().setup();
drajver = new FirefoxDriver();
        drajver.manage().window().maximize();
        drajver.navigate().to("https://www.saucedemo.com/");



    }
}
