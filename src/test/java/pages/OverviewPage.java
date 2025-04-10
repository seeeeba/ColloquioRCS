package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OverviewPage {

    private Commons commons;
    private WebDriver driver;

    public OverviewPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }

    public static By finishButton = By.id("finish");

    public void checkOverviewPage() {
        System.out.println("Controllo sezione overview");
        WebElement burger = driver.findElement(InventoryPage.burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(InventoryPage.cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(InventoryPage.title);
        assertTrue(titleElement.isDisplayed(), "Titolo non trovato!");
        String actualTitle = titleElement.getText();
        assertEquals("Checkout: Overview", actualTitle, "Non sei nella pagina di overview!");
        System.out.println("Titolo corretto: 'Checkout: Overview'.");

        WebElement quantity = driver.findElement(ShoppingCartPage.quantityLabel);
        assertTrue(quantity.isDisplayed(), "Label QTY non trovata!");
        System.out.println("Label QTY trovata.");

        WebElement description = driver.findElement(ShoppingCartPage.descLabel);
        assertTrue(description.isDisplayed(), "Label Description non trovata!");
        System.out.println("Label Description trovata.");

        List<WebElement> cartItems = driver.findElements(By.xpath("//div[@class='cart_item']"));
        int itemCount = cartItems.size();

        WebElement badge = driver.findElement(By.className("shopping_cart_badge"));
        int badgeCount = Integer.parseInt(badge.getText());

        assertEquals(badgeCount, itemCount, "Il numero di prodotti nell'overview non corrisponde al numero del badge.");
        System.out.println("Numero items nel carrello: " + itemCount + " | Numero nel badge: " + badgeCount);

        WebElement cancel = driver.findElement(CheckoutPage.cancelButton);
        assertTrue(cancel.isDisplayed(), "Button Cancel non trovato!");
        System.out.println("Button Cancel trovato.");

        WebElement finish = driver.findElement(finishButton);
        assertTrue(finish.isDisplayed(), "Button Finish non trovato!");
        System.out.println("Button Finish trovato.");

        System.out.println("Sezione overview correttamente visualizzata");
    }

    public void checkItemTotalAmount() {
        System.out.println("Controllo prezzo coerente");
        commons.waitForMilliseconds(1000);
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));

        double sum = 0.0;
        for (WebElement el : priceElements) {
            String priceText = el.getText().replace("$", "").trim();
            sum += Double.parseDouble(priceText);
        }

        WebElement totalElement = driver.findElement(By.xpath("//div[@class='summary_subtotal_label']"));
        String totalText = totalElement.getText(); // es. "Item total: $129.94"
        String totalValueStr = totalText.replace("Item total: $", "").trim();
        double displayedTotal = Double.parseDouble(totalValueStr);

        assertEquals(sum, displayedTotal, 0.01, "Il totale calcolato non corrisponde al totale mostrato.");
        System.out.println("Totale calcolato: " + sum + " | Totale mostrato: " + displayedTotal);
    }
}
