package tests;
import org.junit.jupiter.api.Test;
import utils.BaseTest;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class E2E_04_Problem_user extends BaseTest {

    @Test
    public void testCase_ProblemUser() {

//      tentativo di login con utenza lockedOut
        loginPage.checkLoginPage();

        loginPage.login("lockedOut");

        loginPage.checkErrorsLoginForm();

        loginPage.login("problem");
        assertTrue(inventoryPage.pageLogo(),"La sezione Inventory non è stata caricata correttamente");
        commons.waitForMilliseconds(1000); //secondi inseriti per rendere più analizzabile il flusso
        System.out.println("Atterrato correttamente nella sezione Inventory");

        inventoryPage.checkProductsPage();

        inventoryPage.addProductToCartError();

        inventoryPage.removeItemFromCart();

        inventoryPage.checkFilterFunctionality();

        inventoryPage.checkInventoryCardsDetails();

        inventoryPage.goToShoppingCart();



    }

}
