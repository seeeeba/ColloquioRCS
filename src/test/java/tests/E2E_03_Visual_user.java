package tests;
import org.junit.jupiter.api.Test;
import utils.BaseTest;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class E2E_03_Visual_user extends BaseTest {

    @Test
    public void testCase_VisualUser() {
        loginPage.checkLoginPage();

        loginPage.login("visual");
        assertTrue(inventoryPage.pageLogo(), "La sezione Inventory non è stata caricata correttamente");
        commons.waitForMilliseconds(1000);
        System.out.println("Atterrato correttamente nella sezione Inventory");

        inventoryPage.checkProductsPage();

        inventoryPage.checkFiltersAndPrices();

        inventoryPage.addToCartAndCount();

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