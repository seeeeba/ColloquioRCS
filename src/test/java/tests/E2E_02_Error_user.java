package tests;
import org.junit.jupiter.api.Test;
import utils.BaseTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class E2E_02_Error_user extends BaseTest {

    @Test
    public void testCase_Error() {
        loginPage.checkLoginPage();

        loginPage.login("error");
        assertTrue(inventoryPage.pageLogo(),"La sezione Inventory non è stata caricata correttamente");
        commons.waitForMilliseconds(1000); //secondi inseriti per rendere più analizzabile il flusso
        System.out.println("Atterrato correttamente nella sezione Inventory");

        inventoryPage.checkProductsPage();

        inventoryPage.addProductToCartError();

        inventoryPage.removeItemFromCart();

        inventoryPage.goToShoppingCart();

        shoppingCartPage.checkCartPage();

        checkoutPage.goToCheckout();

        checkoutPage.compileCheckoutError();

        overviewPage.checkOverviewPage();

        overviewPage.checkItemTotalAmount();

        orderPage.goToOrderPage();

        orderPage.checkOrderPage();

    }

}
