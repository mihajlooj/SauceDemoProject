package Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static Base.BaseTest.drajver;

public class CheckoutPage {

    public CheckoutPage(){
        PageFactory.initElements(drajver, this);
    }

    @FindBy(id = "first-name")
    public WebElement FirstNameField;

    @FindBy(id = "last-name")
    public WebElement LastNameField;

    @FindBy(id = "postal-code")
    public WebElement PostalCode;

    @FindBy(id = "continue")
    public WebElement ContinueButton;

    @FindBy(id = "cancel")
    public WebElement CancelButton;

    @FindBy(id = "finish")
    public WebElement FinishButton;

    @FindBy(className = "summary_subtotal_label")
    public WebElement totalPrice;

    @FindBy(className = "complete-header")
    public WebElement checkoutMessage;

    @FindBy(css = ".error-message-container.error")
    public WebElement errorMessage;
    //----------------------------------------------------

    public void FillCheckoutForm(String firstname, String lastname, String zipcode){
        FirstNameField.clear();
        FirstNameField.sendKeys(firstname);
        LastNameField.clear();
        LastNameField.sendKeys(lastname);
        PostalCode.clear();
        PostalCode.sendKeys(zipcode);


    }

    public void inputFirstName(String firstname){
        FirstNameField.clear();
        FirstNameField.sendKeys(firstname);
    }

    public void clickOnContinueButton(){
        ContinueButton.click();
    }

    public void clickOnCancelButton(){
        CancelButton.click();
    }

    public void clickOnFinishButton(){
        FinishButton.click();
    }

    public double TotalPrice(){
       String priceText = totalPrice.getText();
        double price = Double.parseDouble(priceText.replace("$",""));
        return price;
    }

}
