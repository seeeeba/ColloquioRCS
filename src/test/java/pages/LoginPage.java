package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.WaitUtils;

public class LoginPage {
    private WebDriver driver;
    private WaitUtils wait;

    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WaitUtils(driver, 10);
    }

    public void login(String username, String password) {
        wait.waitForElementVisible(usernameField).sendKeys(username);
        wait.waitForElementVisible(passwordField).sendKeys(password);
        wait.waitForElementClickable(loginButton).click();
    }

    public String getLoginErrorMessage() {
        return wait.waitForElementVisible(errorMessage).getText();
    }

    public boolean isErrorVisible() {
        return wait.waitForElementVisible(errorMessage).isDisplayed();
    }
}
