package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.Commons;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InventoryPage {
    private WebDriver driver;
    private Commons commons;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }


    public static By pageLogo = By.xpath("//div[@class='app_logo']");
    public static By title = By.xpath("//span[@class='title']");
    public static By burgerMenu = By.id("react-burger-menu-btn");
    public static By cartIcon = By.className("shopping_cart_link");
    public static By filterProducts = By.className("product_sort_container");
    public static By productCards = By.className("inventory_item");
    public static By twitterIcon = By.className("social_twitter");
    public static By facebookIcon = By.className("social_facebook");
    public static By linkedinIcon = By.className("social_linkedin");
    public static By addToCartButton = By.xpath("//*[contains(@id,'add-to-cart')]");
    public static By shoppingCartNumber = By.xpath("//span[@class='shopping_cart_badge']");
    public static By shoppingCartButton = By.className("shopping_cart_link");


    public boolean pageLogo() {
        return commons.isElementVisible(pageLogo);
    }

    public void checkProductsPage() {
        System.out.println("Controllo sezione products");
        WebElement burger = driver.findElement(burgerMenu);
        assertTrue(burger.isDisplayed(), "Burger menu non trovato!");
        System.out.println("Burger menu trovato.");

        WebElement cart = driver.findElement(cartIcon);
        assertTrue(cart.isDisplayed(), "Icona carrello non trovata!");
        System.out.println("Icona carrello trovata.");

        WebElement titleElement = driver.findElement(title);
        assertTrue(titleElement.isDisplayed(), "Titolo non trovato!");
        String actualTitle = titleElement.getText();
        assertEquals("Products", actualTitle, "Non sei nella pagina del carrello!");
        System.out.println("Titolo corretto: 'Products'.");

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

    public void addToCartAndCount() {
        List<WebElement> addToCartButtons = driver.findElements(addToCartButton);

        for (int i = 0; i < addToCartButtons.size(); i++) {
            commons.waitForMilliseconds(500);
            addToCartButtons.get(i).click();

            WebElement cartBadge = driver.findElement(shoppingCartNumber);
            int badgeCount = Integer.parseInt(cartBadge.getText());

            assertEquals(i + 1, badgeCount, "Numero nel carrello errato dopo aggiunta dell'elemento " + (i + 1));
            System.out.println("Elemento " + (i + 1) + " aggiunto correttamente. Badge: " + badgeCount);
        }
        commons.waitForMilliseconds(500);
        System.out.println("Gli elementi vengono aggiunti correttamente al carrello.");
    }

    public List<String> getProductNames() {
        List<WebElement> nameElements = driver.findElements(By.className("inventory_item_name"));
        return nameElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public List<Double> getProductPrices() {
        List<WebElement> priceElements = driver.findElements(By.className("inventory_item_price"));
        return priceElements.stream()
                .map(el -> Double.parseDouble(el.getText().replace("$", "").trim()))
                .collect(Collectors.toList());
    }

    public void selectFilter(String visibleText) {
        Select filterSelect = new Select(driver.findElement(By.className("product_sort_container")));
        filterSelect.selectByVisibleText(visibleText);
        commons.waitForMilliseconds(500);
    }

    public void checkFilterFunctionality(){
        System.out.println("Avvio controllo funzionalità filtri...");

        System.out.println("Verifica ordinamento iniziale 'Name (A to Z)'");
        List<String> namesAZ = getProductNames();
        List<String> expectedAZ = new ArrayList<>(namesAZ);
        Collections.sort(expectedAZ);
        if (expectedAZ.equals(namesAZ)) {
            System.out.println("Ordinamento 'Name (A to Z)' verificato con successo.");
        } else {
            System.out.println("Filtro 'Name (A to Z)' non funzionante.");
        }
        commons.waitForMilliseconds(1000);

        selectFilter("Name (Z to A)");
        System.out.println("Verifica ordinamento 'Name (Z to A)'");
        List<String> namesZA = getProductNames();
        List<String> expectedZA = new ArrayList<>(namesZA);
        Collections.sort(expectedZA, Collections.reverseOrder());
        if (expectedZA.equals(namesZA)) {
            System.out.println("Ordinamento 'Name (Z to A)' verificato con successo.");
        } else {
            System.out.println("Filtro 'Name (Z to A)' non funzionante.");
        }
        commons.waitForMilliseconds(1000);

        selectFilter("Price (high to low)");
        System.out.println("Verifica ordinamento 'Price (high to low)'");
        List<Double> pricesHighToLow = getProductPrices();
        List<Double> expectedHighToLow = new ArrayList<>(pricesHighToLow);
        expectedHighToLow.sort(Collections.reverseOrder());
        if (expectedHighToLow.equals(pricesHighToLow)) {
            System.out.println("Ordinamento prezzi da alto a basso verificato con successo.");
        } else {
            System.out.println("Filtro 'Price (high to low)' non funzionante.");
        }
        commons.waitForMilliseconds(1000);

        selectFilter("Price (low to high)");
        System.out.println("Verifica ordinamento 'Price (low to high)'");
        List<Double> pricesLowToHigh = getProductPrices();
        List<Double> expectedLowToHigh = new ArrayList<>(pricesLowToHigh);
        Collections.sort(expectedLowToHigh);
        if (expectedLowToHigh.equals(pricesLowToHigh)) {
            System.out.println("Ordinamento prezzi da basso ad alto verificato con successo.");
        } else {
            System.out.println("Filtro 'Price (low to high)' non funzionante.");
        }
        commons.waitForMilliseconds(1000);

        System.out.println("Controllo filtri completato.");
    }

    public void goToShoppingCart() {
        WebElement cartButton = driver.findElement(shoppingCartButton);
        cartButton.click();
    }

    public void addProductToCartError() {
        List<WebElement> cards = driver.findElements(By.className("inventory_item"));
        int countAdded = 0;

        for (int i = 0; i < cards.size(); i++) {
            WebElement card = cards.get(i);
            WebElement button = card.findElement(By.xpath(".//button[contains(@id,'add-to-cart')]"));
            button.click();
            commons.waitForMilliseconds(300);

            WebElement updatedCard = driver.findElements(By.className("inventory_item")).get(i);
            WebElement updatedButton = updatedCard.findElement(By.xpath(".//button"));
            String after = updatedButton.getText();

            if (after.equalsIgnoreCase("Remove")) {
                countAdded++;
                System.out.println("Prodotto " + (i + 1) + ": aggiunto correttamente.");
            } else {
                System.out.println("Prodotto " + (i + 1) + ": il bottone non è cambiato (rimane '" + after + "').");
            }
        }

        int badgeCount = 0;
        List<WebElement> badgeElements = driver.findElements(By.className("shopping_cart_badge"));
        if (!badgeElements.isEmpty()) {
            badgeCount = Integer.parseInt(badgeElements.get(0).getText());
        }

        System.out.println("Prodotti aggiunti nel carrello: " + countAdded);
        System.out.println("Numero item del carrello: " + badgeCount);

        assertEquals(countAdded, badgeCount, "Il badge del carrello non corrisponde ai prodotti aggiunti.");

    }
    public void removeItemFromCart() {
        List<WebElement> cards = driver.findElements(By.className("inventory_item"));
        int countRemoved = 0;

        for (int i = 0; i < cards.size(); i++) {
            WebElement card = driver.findElements(By.className("inventory_item")).get(i);
            WebElement button = card.findElement(By.xpath(".//button"));

            String text = button.getText();
            if (!text.equalsIgnoreCase("Remove")) {
                continue;
            }

            button.click();
            commons.waitForMilliseconds(300);

            WebElement updatedCard = driver.findElements(By.className("inventory_item")).get(i);
            WebElement updatedButton = updatedCard.findElement(By.xpath(".//button"));
            String after = updatedButton.getText();

            if (after.equalsIgnoreCase("Add to cart")) {
                countRemoved++;
                System.out.println("Prodotto " + (i + 1) + ": rimosso correttamente.");
            } else {
                System.out.println("Prodotto " + (i + 1) + ": non è stato possibile rimuovere l'item dal carrello.");
            }
        }

    }

    public void checkInventoryCardsDetails() {
        System.out.println("Avvio del controllo di coerenza tra le card dell'inventario e le relative pagine di dettaglio.");

        List<WebElement> cards = driver.findElements(By.className("inventory_item"));

        for (int i = 0; i < cards.size(); i++) {
            System.out.println("Controllo del prodotto numero " + (i + 1));

            cards = driver.findElements(By.className("inventory_item"));
            WebElement card = cards.get(i);

            String name = card.findElement(By.className("inventory_item_name")).getText();
            String price = card.findElement(By.className("inventory_item_price")).getText();
            String description = card.findElement(By.className("inventory_item_desc")).getText();

            System.out.println("Dati letti dalla card:");
            System.out.println("Nome: " + name);
            System.out.println("Prezzo: " + price);
            System.out.println("Descrizione: " + description);

            card.findElement(By.className("inventory_item_name")).click();
            commons.waitForMilliseconds(1000);

            String detailName = driver.findElement(By.className("inventory_details_name")).getText();
            String detailPrice = driver.findElement(By.className("inventory_details_price")).getText();
            String detailDescription = driver.findElement(By.className("inventory_details_desc")).getText();

            if (name.equals(detailName) && price.equals(detailPrice) && description.equals(detailDescription)) {
                System.out.println("I dati del prodotto coincidono correttamente nella pagina di dettaglio.");
            } else {
                System.out.println("I dati del prodotto NON coincidono con la pagina di dettaglio.");
            }

            driver.navigate().back();
            commons.waitForMilliseconds(1000);
        }

        System.out.println("Controllo completato per tutte le card.");
    }

    public void checkAboutSection() {

        System.out.println("Apertura del menu laterale.");
        WebElement burger = driver.findElement(burgerMenu);
        burger.click();
        commons.waitForMilliseconds(1000);

        System.out.println("Clic sul link About.");
        WebElement aboutLink = driver.findElement(By.id("about_sidebar_link"));
        aboutLink.click();
        commons.waitForMilliseconds(1500);

        String currentUrl = driver.getCurrentUrl();
        System.out.println("URL della pagina About: " + currentUrl);

        if (currentUrl.contains("error/404")) {
            System.out.println("Il link About porta correttamente a una pagina con error 404.");
        } else {
            System.out.println("Il link About non porta a una pagina 404.");
        }

    }

}