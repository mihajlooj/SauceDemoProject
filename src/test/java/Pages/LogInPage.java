package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LogInPage extends BaseTest {

    public LogInPage() {
        PageFactory.initElements(drajver, this);
    }

    @FindBy(name = "login-button")
    public WebElement Loginbutton;

    @FindBy(id = "user-name")
    public WebElement UsernameField;

    @FindBy(id = "password")
    public WebElement PasswordField;

    @FindBy(css = ".error-message-container.error")
    public WebElement ErrorMessage;

    //------------------------------------------------------------

    public void clickOnLoginButton() {
            Loginbutton.click();
    }

    public void UsernameFieldInput(String username){
        UsernameField.clear();
        UsernameField.sendKeys(username);
    }

    public void PasswordFieldInput(String password){
        PasswordField.clear();
        PasswordField.sendKeys(password);
    }


}
