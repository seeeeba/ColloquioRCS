package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ShoppingCartPage {
    private Commons commons;
    private WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }

    public static By quantityLabel = By.className("cart_quantity_label");
    public static By descLabel = By.className("cart_desc_label");
    public static By continueButton = By.id("continue-shopping");
    public static By checkoutButton = By.id("checkout");


    public void checkCartPage() {

        WebElement burger = driver.findElement(InventoryPage.burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(InventoryPage.cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(InventoryPage.title);
        assertTrue(titleElement.isDisplayed(), "Titolo non trovato!");
        String actualTitle = titleElement.getText();
        assertEquals("Your Cart", actualTitle, "Non sei nella pagina del carrello!");
        System.out.println("Titolo corretto: 'Your Cart'.");

        WebElement quantity = driver.findElement(quantityLabel);
        assertTrue(quantity.isDisplayed(), "Label QTY non trovata!");
        System.out.println("Label QTY trovata.");

        WebElement description = driver.findElement(descLabel);
        assertTrue(description.isDisplayed(), "Label Description non trovata!");
        System.out.println("Label Description trovata.");

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

        System.out.println("La pagina del carrello è visualizzata correttamente!");
    }

}
