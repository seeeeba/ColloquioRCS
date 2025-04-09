package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShoppingCartPage {
    private WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    By title = By.xpath("//span[@class='title']");
    By burgerMenu = By.id("react-burger-menu-btn");
    By cartIcon = By.className("shopping_cart_link");
    By quantityLabel = By.className("cart_quantity_label");
    By descLabel = By.className("cart_desc_label");
    By continueButton = By.id("continue-shopping");
    By checkoutButton = By.id("checkout");


    public void checkCartPage() {

        WebElement burger = driver.findElement(burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(title);
        assertTrue(titleElement.isDisplayed(), "Titolo non trovato!");
        String actualTitle = titleElement.getText();
        assertEquals("Your Cart", actualTitle, "Non sei nella pagina del carrello!");
        System.out.println("Titolo corretto: 'Your Cart'.");

        WebElement quantity = driver.findElement(quantityLabel);
        assertTrue(quantity.isDisplayed(), "Label QTY non trovata!");
        System.out.println("Label QTY trovata.");

        WebElement description = driver.findElement(descLabel);
        assertTrue(description.isDisplayed(), "Label description non trovata!");
        System.out.println("Label description trovata.");

        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
        int itemCount = cartItems.size();

        WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
        int badgeCount = Integer.parseInt(badge.getText());

        assertEquals(badgeCount, itemCount, "Il numero di prodotti nel carrello non corrisponde al numero del badge.");
        System.out.println("Numero card nel carrello: " + itemCount + " | Numero nel badge: " + badgeCount);

        WebElement continueShoppingButton = driver.findElement(continueButton);
        assertTrue(continueShoppingButton.isDisplayed(), "Button Continue Shopping non trovato!");
        System.out.println("Button Continue Shopping trovato.");

        WebElement checkout = driver.findElement(checkoutButton);
        assertTrue(checkout.isDisplayed(), "Button Checkout non trovato!");
        System.out.println("Button Checkout trovato.");

    }


}
