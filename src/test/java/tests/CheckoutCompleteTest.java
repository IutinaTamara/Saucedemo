package tests;

import org.testng.annotations.Test;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCorrectData;

public class CheckoutCompleteTest extends BaseTest {
    @Test()
    public void switchToProducts() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.switchToCart();

        cartPage.checkOut();

        informationPage.data(withCorrectData());

        overviewPage.switchToComplete();

        completePage.switchToProducts("#back-to-products");

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());
    }
}
