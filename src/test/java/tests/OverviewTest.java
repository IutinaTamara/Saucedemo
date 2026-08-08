package tests;

import org.testng.annotations.Test;

import static enums.TitleNaming.CHECKOUT3;
import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCorrectData;

public class OverviewTest extends BaseTest {
    @Test()
    public void switchToComplete() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.switchToCart();

        cartPage.checkOut();

        informationPage.data(withCorrectData());

        overviewPage.switchToComplete();

        assertTrue(completePage.pageIsOpen());
        assertEquals(completePage.getNamePage(), CHECKOUT3.getDisplayName());
    }

    @Test()
    public void switchToProducts() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.switchToCart();

        cartPage.checkOut();

        informationPage.data(withCorrectData());

        overviewPage.switchToProducts("#cancel");

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());
    }
}
