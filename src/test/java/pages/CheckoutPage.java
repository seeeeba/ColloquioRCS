package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CheckoutPage {
    private Commons commons;
    private WebDriver driver;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }

    public static By firstNameInput = By.id("first-name");
    public static By lastNameInput = By.id("last-name");
    public static By postalCodeInput = By.id("postal-code");
    public static By continueButton = By.id("continue");
    public static By cancelButton = By.id("cancel");


    public void goToCheckout() {
        WebElement checkout = driver.findElement(ShoppingCartPage.checkoutButton);
        commons.waitForMilliseconds(500);
        checkout.click();
    }

    public void checkCheckoutPage() {
        System.out.println("Controllo sezione checkout");
        WebElement burger = driver.findElement(InventoryPage.burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(InventoryPage.cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(InventoryPage.title);
        assertTrue(titleElement.isDisplayed(), "Titolo non trovato!");
        String actualTitle = titleElement.getText();
        assertEquals("Checkout: Your Information", actualTitle, "Non sei nella pagina del checkout!");
        System.out.println("Titolo corretto: 'Checkout: Your Information'.");

        WebElement firstName = driver.findElement(firstNameInput);
        assertTrue(firstName.isDisplayed(), "Input First name non trovato!");
        System.out.println("Input First name trovato.");

        WebElement lastName = driver.findElement(lastNameInput);
        assertTrue(lastName.isDisplayed(), "Input Last name non trovato!");
        System.out.println("Input Last name trovato.");

        WebElement postalCode = driver.findElement(postalCodeInput);
        assertTrue(postalCode.isDisplayed(), "Input Postal code non trovato!");
        System.out.println("Input Postal code trovato.");

        WebElement cancelButtonCheckout = driver.findElement(cancelButton);
        assertTrue(cancelButtonCheckout.isDisplayed(), "Button Cancel non trovato!");
        System.out.println("Button Cancel trovato.");

        WebElement continueButtonCheckout = driver.findElement(continueButton);
        assertTrue(continueButtonCheckout.isDisplayed(), "Button Continua non trovato!");
        System.out.println("Button Continua trovato.");

        System.out.println("Sezione checkout correttamente visualizzata");
    }

    public void compileCheckoutForm() {
        WebElement firstName = driver.findElement(firstNameInput);
        firstName.sendKeys("Mario");
        commons.waitForMilliseconds(500);

        WebElement lastName = driver.findElement(lastNameInput);
        lastName.sendKeys("Rossi");
        commons.waitForMilliseconds(500);

        WebElement postalCode = driver.findElement(postalCodeInput);
        postalCode.sendKeys("12345");
        commons.waitForMilliseconds(500);

        String firstNameValue = firstName.getAttribute("value");
        if (firstNameValue == null || firstNameValue.isEmpty()) {
            System.out.println("Il campo First Name non è stato compilato correttamente.");
        } else {
            System.out.println("Il campo First Name è stato compilato con successo: " + firstNameValue);
        }

        String lastNameValue = lastName.getAttribute("value");
        if (lastNameValue == null || lastNameValue.isEmpty()) {
            System.out.println("Il campo Last Name non è stato compilato correttamente.");
        } else {
            System.out.println("Il campo Last Name è stato compilato con successo: " + lastNameValue);
        }

        String postalCodeValue = postalCode.getAttribute("value");
        if (postalCodeValue == null || postalCodeValue.isEmpty()) {
            System.out.println("Il campo Postal Code non è stato compilato correttamente.");
        } else {
            System.out.println("Il campo Postal Code è stato compilato con successo: " + postalCodeValue);
        }

        WebElement continueButtonCheckout = driver.findElement(continueButton);
        continueButtonCheckout.click();

        commons.waitForMilliseconds(1000); // Attendi caricamento pagina

        List<WebElement> titles = driver.findElements(By.className("title"));
        if (!titles.isEmpty()) {
            String titleText = titles.get(0).getText();
            if (titleText.equals("Checkout: Overview")) {
                System.out.println("Navigazione corretta alla pagina Checkout Overview.");
            } else {
                System.out.println("Non è stato possibile atterrare sulla pagina Checkout Overview.");
            }
        }

    }

}
