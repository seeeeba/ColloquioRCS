package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class Commons {

    private WebDriver driver;
    private WebDriverWait commons;

    public Commons(WebDriver driver, int timeoutInSeconds) {
        this.driver = driver;
        this.commons = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    public WebElement waitForElementVisible(By locator) {
        return commons.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public WebElement waitForElementClickable(By locator) {
        return commons.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public boolean isElementVisible(By locator) {
        try {
            waitForElementVisible(locator);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean isElementPresent(By locator) {
        return !driver.findElements(locator).isEmpty();
    }

    public void waitForMilliseconds(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    public void returnToHomePage() {
        String homeTitle = "Products";
        boolean atHome = false;

        while (!atHome) {
            waitForMilliseconds(500);

            List<WebElement> cancelButtons = driver.findElements(By.id("cancel"));
            List<WebElement> continueShoppingButtons = driver.findElements(By.id("continue-shopping"));
            List<WebElement> backHomeButtons = driver.findElements(By.id("back-to-products"));

            if (!cancelButtons.isEmpty()) {
                cancelButtons.get(0).click();
                System.out.println("Pulsante 'Cancel' cliccato.");
            } else if (!continueShoppingButtons.isEmpty()) {
                continueShoppingButtons.get(0).click();
                System.out.println("Pulsante 'Continue Shopping' cliccato.");
            } else if (!backHomeButtons.isEmpty()) {
                backHomeButtons.get(0).click();
                System.out.println("Pulsante 'Back Home' cliccato.");
            } else {
                System.out.println("Nessun pulsante per tornare alla homepage trovato.");
                break;
            }

            waitForMilliseconds(500);

            List<WebElement> titles = driver.findElements(By.className("title"));
            if (!titles.isEmpty()) {
                String titleText = titles.get(0).getText();
                if (titleText.equals(homeTitle)) {
                    System.out.println("Sei tornato correttamente alla homepage con titolo: " + homeTitle);
                    atHome = true;
                } else {
                    System.out.println("Titolo pagina: " + titleText + ". Nuovo tentativo.");
                }
            }
        }
    }
}