package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    List<String> goodList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Backpack",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        for (String goodName : goodList) {
            productsPage.addToCart(goodName);
        }
        productsPage.switchToCart();

        assertTrue(cartPage.pageIsOpen());
        assertEquals(cartPage.getNamePage(), "Your Cart");

        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));
        assertEquals(cartPage.getProductsNames(), goodList);
    }
}
