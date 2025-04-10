package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.ConfigReader;
import utils.Commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginPage {
    private WebDriver driver;
    private Commons commons;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");
    private final By errorButton = By.cssSelector("[data-test='error-button']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.commons = new Commons(driver, 10);
    }

    public void login(String username, String password) {
        commons.waitForElementVisible(usernameField).sendKeys(username);
        commons.waitForElementVisible(passwordField).clear();
        commons.waitForElementVisible(passwordField).sendKeys(password);
        commons.waitForElementClickable(loginButton).click();
        if (commons.isElementPresent(InventoryPage.pageLogo)) {
            System.out.println("Login eseguito con utente: " + username);
        } else {
            System.out.println("Login non riuscita con utente: " + username);
        }

    }

    public void login(String userType) {
        String user = ConfigReader.getUser(userType != null ? userType : "standard");
        String password = ConfigReader.get("base.password");
        login(user, password);
    }

    public void checkLoginPage() {
        System.out.println("Controllo pagina login");
        WebElement userName = driver.findElement(usernameField);
        assertTrue(userName.isDisplayed(), "Input UserName non trovato!");
        System.out.println("Input UserName trovato.");

        WebElement password = driver.findElement(passwordField);
        assertTrue(password.isDisplayed(), "Input Password non trovato!");
        System.out.println("Input Password trovato.");

        WebElement login = driver.findElement(loginButton);
        assertTrue(login.isDisplayed(), "Button Login non trovato!");
        System.out.println("Button Login trovato.");

        System.out.println("Sezione login correttamente visualizzata");
    }

    public void checkErrorsLoginForm() {

        WebElement error = driver.findElement(errorMessage);
        assertTrue(error.isDisplayed(), "Messaggio di errore non visualizzato.");

        String expectedMessage = "Epic sadface: Sorry, this user has been locked out.";
        String actualMessage = error.getText();
        assertEquals(expectedMessage, actualMessage, "Il messaggio di errore non è quello atteso.");
        System.out.println("Messaggio di errore corretto: \"" + actualMessage + "\"");

        WebElement XButton = driver.findElement(errorButton);
        assertTrue(XButton.isDisplayed(), "Bottone per chiudere l'errore non trovato.");
        System.out.println("Bottone X per chiusura errore visibile.");

        commons.waitForMilliseconds(500);

        resetLoginForm();

        WebElement username = driver.findElement(usernameField);
        username.sendKeys("mario");

        WebElement password = driver.findElement(passwordField);
        password.sendKeys("test");

        commons.waitForMilliseconds(500);
        WebElement login = driver.findElement(loginButton);
        login.click();

        commons.waitForMilliseconds(500);

        WebElement errorUnmatchedUser = driver.findElement(errorMessage);
        assertTrue(errorUnmatchedUser.isDisplayed(), "Messaggio di errore non visualizzato.");

        String expectedMessageUser = "Epic sadface: Username and password do not match any user in this service";
        String actualMessageUser = errorUnmatchedUser.getText();
        assertEquals(expectedMessageUser, actualMessageUser, "Il messaggio di errore non è quello atteso.");
        System.out.println("Messaggio di errore corretto: \"" + actualMessageUser + "\"");

        resetLoginForm();

    }

    private void resetLoginForm(){
        WebElement XButton = driver.findElement(errorButton);
        XButton.click();
        commons.waitForMilliseconds(500);
        WebElement username = driver.findElement(usernameField);
        username.clear();
        commons.waitForMilliseconds(500);
        WebElement password = driver.findElement(passwordField);
        password.clear();
        commons.waitForMilliseconds(500);
        System.out.println("Resettato il login form");
    }

}