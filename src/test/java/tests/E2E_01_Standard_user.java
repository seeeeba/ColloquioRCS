package tests;
import org.junit.jupiter.api.Test;
import utils.BaseTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class E2E_01_Standard_user extends BaseTest {

    @Test
    public void testCase_Standard() {

        loginPage.checkLoginPage();

        loginPage.login("standard");
        assertTrue(inventoryPage.pageLogo(),"La sezione Inventory non è stata caricata correttamente");
        commons.waitForMilliseconds(1000); //secondi inseriti per rendere più analizzabile il flusso
        System.out.println("Atterrato correttamente nella sezione Inventory");

        inventoryPage.checkProductsPage();

        inventoryPage.checkFilterFunctionality();

        inventoryPage.addToCartAndCount();

        inventoryPage.removeItemFromCart();

        inventoryPage.addToCartAndCount();

        inventoryPage.checkInventoryCardsDetails();

        inventoryPage.goToShoppingCart();

        shoppingCartPage.checkCartPage();

        checkoutPage.goToCheckout();

        checkoutPage.checkCheckoutPage();

        checkoutPage.compileCheckoutForm();

        overviewPage.checkOverviewPage();

        overviewPage.checkItemTotalAmount();

        orderPage.goToOrderPage();

        orderPage.checkOrderPage();
    }

}
