package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {

    public CartPage() {
        PageFactory.initElements(drajver, this);
    }

    @FindBy(id = "continue-shopping")
    public WebElement ContinueShoppingButton;

    @FindBy(id = "react-burger-menu-btn")
    public WebElement MenuButton;

    @FindBy(id = "inventory_sidebar_link")
    public WebElement AllItemsButton;

    @FindBy(id = "about_sidebar_link")
    public WebElement AboutButton;

    @FindBy(id = "logout_sidebar_link")
    public WebElement LogOutButton;

    @FindBy(id = "reset_sidebar_link")
    public WebElement ResetButton;

    @FindBy(className = "shopping_cart_badge")
    public WebElement ShoppingCartItemCounter;

    @FindBy(css = "button[id^='remove-']")
    public WebElement RemoveItem;

    @FindBy(css = "button[id^='remove-']")
    public List<WebElement> RemoveItems;

    @FindBy(className = "inventory_item_price")
    public WebElement itemprice;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> listOfItems;

    @FindBy(id = "checkout")
    public WebElement checkoutButton;


    //----------------------------------------------------

    public void clickOnMenuButton() {
        MenuButton.click();
    }

    public void clickOnAllItemsButton() {
        AllItemsButton.click();
    }

    public void clickOnAboutButton() {
        AboutButton.click();
    }

    public void clickOnLogOutButton() {
        LogOutButton.click();
    }

    public void clickOnResetButton() {
        ResetButton.click();
    }

    public void clickOnRemoveItem() {
        RemoveItem.click();
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

    public void RemoveAllItems() {
        for (WebElement item : RemoveItems) {
            clickOnRemoveItem();
        }
    }

    public double getItemPrice(){
        String priceText = itemprice.getText();
        double price = Double.parseDouble(priceText.replace("$",""));
        return price;
    }

    public double checkPrice() {
        double price = 0.00;
        for(WebElement item : listOfItems) {
            String priceText = itemprice.getText();
            price = Double.parseDouble(priceText.replace("$", ""));
        }
        return price;
    }

}
