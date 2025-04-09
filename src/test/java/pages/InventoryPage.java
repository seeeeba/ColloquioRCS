package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryPage {
    private WebDriver driver;
    private Commons commons;

    By pageLogo = By.xpath("//div[@class='app_logo']");
    By title = By.xpath("//span[@class='title']");
    By burgerMenu = By.id("react-burger-menu-btn");
    By cartIcon = By.className("shopping_cart_link");
    By filterProducts = By.className("product_sort_container");
    By productCards = By.className("inventory_item");
    By twitterIcon = By.className("social_twitter");
    By facebookIcon = By.className("social_facebook");
    By linkedinIcon = By.className("social_linkedin");
    By addToCartButton = By.xpath("//*[contains(@id,'add-to-cart')]");
    By shoppingCartNumber = By.xpath("//span[@class='shopping_cart_badge']");
    By productButtons = By.cssSelector(".inventory_item button");
    By productImages = By.cssSelector(".inventory_item_img img");

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }

    public boolean pageLogo() {
        return commons.isElementVisible(pageLogo);
    }

    public void checkProductsPage() {

        WebElement burger = driver.findElement(burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(title);
        assertTrue(titleElement.isDisplayed(), "Titolo 'Products' non trovato!");
        System.out.println("Titolo 'Products' trovato.");

        WebElement filter = driver.findElement(filterProducts);
        assertTrue(filter.isDisplayed(), "Select filtri non trovato!");
        System.out.println("Select filtri trovato.");

        List<WebElement> cards = driver.findElements(productCards);
        assertEquals(6, cards.size(), "Numero di prodotti errato, le card trovate sono: " + cards.size());
        System.out.println("Trovate 6 card prodotto.");

        WebElement twitter = driver.findElement(twitterIcon);
        assertTrue(twitter.isDisplayed(), "Icona Twitter non trovata!");
        System.out.println("Icona Twitter trovata!");

        WebElement facebook = driver.findElement(facebookIcon);
        assertTrue(facebook.isDisplayed(), "Icona Facebook non trovata!");
        System.out.println("Icona Facebook trovata!");

        WebElement linkedin = driver.findElement(linkedinIcon);
        assertTrue(linkedin.isDisplayed(), "Icona Linkedin non trovata!");
        System.out.println("Icona Linkedin trovata!");

        System.out.println("Pagina Products completa.");
    }

    public void chromePopup() {
        commons.waitForMilliseconds(5000);
        driver.switchTo().alert().accept();
    }

    public void addToCartAndCount() {
        List<WebElement> addToCartButtons = driver.findElements(addToCartButton);

        for (int i = 0; i < addToCartButtons.size(); i++) {
            addToCartButtons.get(i).click();

            WebElement cartBadge = driver.findElement(shoppingCartNumber);
            int badgeCount = Integer.parseInt(cartBadge.getText());

            assertEquals(i + 1, badgeCount, "Numero nel carrello errato dopo aggiunta dell'elemento " + (i + 1));
            System.out.println("Elemento " + (i + 1) + " aggiunto correttamente. Badge: " + badgeCount);
        }
        System.out.println("Gli elementi vengono aggiunti correttamente al carrello.");
    }

    public void clickAddToCartByIndex(int index) {
        List<WebElement> buttons = driver.findElements(productButtons);
        commons.waitForElementClickable(productButtons).click();
        buttons.get(index).click();
    }

    public void clickRemoveFromCartByIndex(int index) {
        List<WebElement> buttons = driver.findElements(productButtons);
        buttons.get(index).click();
    }

    public String getButtonTextByIndex(int index) {
        return driver.findElements(productButtons).get(index).getText();
    }

    public List<String> getAllImageSources() {
        return driver.findElements(productImages).stream().map(e -> e.getAttribute("src")).collect(Collectors.toList());
    }

    public List<Integer> getButtonXPositions() {
        return driver.findElements(productButtons).stream().map(e -> e.getLocation().getX()).collect(Collectors.toList());
    }
}