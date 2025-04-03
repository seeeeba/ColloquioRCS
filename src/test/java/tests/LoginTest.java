package tests;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.ConfigReader;

public class LoginTest extends BaseTest {


    @Test
    void LoginTest() {
        System.out.println("Apertura browser al sito: " + driver.getTitle());
        String username = ConfigReader.getSelectedUser();
        String password = ConfigReader.get("base.password");

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);

        System.out.println("Login eseguito con utente: " + username);
    }
}