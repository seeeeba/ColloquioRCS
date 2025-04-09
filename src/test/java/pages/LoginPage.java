package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ConfigReader;
import utils.Commons;

public class LoginPage {
    private WebDriver driver;
    private Commons wait;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new Commons(driver, 10);
    }

    public void login(String username, String password) {
        wait.waitForElementVisible(usernameField).sendKeys(username);
        wait.waitForElementVisible(passwordField).sendKeys(password);
        wait.waitForElementClickable(loginButton).click();
        System.out.println("Login eseguito con utente: " + username);
    }

    public void login(String userType) {
        String user = ConfigReader.getUser(userType != null ? userType : "standard");
        String password = ConfigReader.get("base.password");
        login(user, password);
    }

    public String getLoginErrorMessage() {
        return wait.waitForElementVisible(errorMessage).getText();
    }

    public boolean isErrorVisible() {
        return wait.waitForElementVisible(errorMessage).isDisplayed();
    }
}