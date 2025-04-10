package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderPage {

    private Commons commons;
    private WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }

    public static By orderCompleteIcon = By.className("pony_express");
    public static By orderTitle = By.className("complete-header");
    public static By completeText = By.className("complete-text");
    public static By backHomeButton = By.id("back-to-products");

    public void goToOrderPage() {
        driver.findElement(OverviewPage.finishButton).click();
    }

    public void checkOrderPage() {
        System.out.println("Controllo sezione riepilogo ordine");
        WebElement burger = driver.findElement(InventoryPage.burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(InventoryPage.cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(InventoryPage.title);
        assertTrue(titleElement.isDisplayed(), "Titolo non trovato!");
        String actualTitle = titleElement.getText();
        assertEquals("Checkout: Complete!", actualTitle, "Non sei nella pagina del riepilogo ordine!");
        System.out.println("Titolo corretto: 'Checkout: Complete!'.");

        WebElement orderIcon = driver.findElement(orderCompleteIcon);
        assertTrue(orderIcon.isDisplayed(), "Icona ordine completato non trovata!");
        System.out.println("Icona ordine completato trovata.");

        WebElement orderText = driver.findElement(orderTitle);
        assertTrue(orderText.isDisplayed(), "'Thank you for your order' non trovato!");
        System.out.println("'Thank you for your order' trovato.");

        WebElement orderDescription = driver.findElement(completeText);
        assertTrue(orderDescription.isDisplayed(), "'Your order has been dispatched, and will arrive just as fast as the pony can get there' non trovato!");
        System.out.println("'Your order has been dispatched, and will arrive just as fast as the pony can get there' trovato.");

        WebElement orderButton = driver.findElement(backHomeButton);
        assertTrue(orderButton.isDisplayed(), "Back home button non trovato!");
        System.out.println("Back home button trovato.");

        System.out.println("Sezione riepilogo ordine correttamente visualizzata");

    }

}
