package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v140.page.Page;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends BaseTest {

    public HomePage(){
        PageFactory.initElements(drajver, this);
    }

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

    @FindBy(id = "shopping_cart_container")
    public WebElement ShoppingCartButton;

    @FindBy(className = "shopping_cart_badge")
    public WebElement ShoppingCartItemCounter;

    @FindBy(css = "button[id^='add-to-cart-']")
    public WebElement AddToCartButton;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> listOfItems;

    @FindBy(className = "inventory_item_price")
    public WebElement itemprice;

    @FindBy(className = "product_sort_container")
    public WebElement itemSorterDropDown;

    @FindBy(className = "product_sort_container")
    public List<WebElement> itemSorter;

    //--------------------------------------------------------

    public void clickOnMenuButton(){
        MenuButton.click();
    }

    public void clickOnAllItemsButton(){
        AllItemsButton.click();
    }

    public void clickOnAboutButton(){
        AboutButton.click();
    }

    public void clickOnLogOutButton(){
        LogOutButton.click();
    }

    public void clickOnResetButton(){
        ResetButton.click();
    }

    public void clickOnShoppingCartButton(){
        ShoppingCartButton.click();
    }

    public void clickOnAddToCartButton(){
        AddToCartButton.click();
    }

    public double getItemPrice(){
        String priceText = itemprice.getText();
        double price = Double.parseDouble(priceText.replace("$",""));
        return price;
    }

    public void AddAllItemsInCart() throws InterruptedException {
        for (WebElement item : listOfItems) {
            clickOnAddToCartButton();
        }
    }

    public double addEveryItemAndCheckItemPrice() {
        double price = 0.00;
        for(WebElement item : listOfItems) {
            AddToCartButton.click();
            String priceText = itemprice.getText();
            price = Double.parseDouble(priceText.replace("$", ""));
        }
        return price;
    }
}
