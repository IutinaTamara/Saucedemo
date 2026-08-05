package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static user.UserFactory.withAdminPermission;

public class ProductsTest extends BaseTest {
    List<String> goodList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Backpack",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        System.out.println("ProductsTest.checkGoodsAdded running in thread: " + Thread.currentThread().getName());
        loginPage.open();
        loginPage.login(withAdminPermission());
        productsPage.pageIsOpen();

        for (String goodName : goodList) {
            productsPage.addToCart(goodName);
        }

        assertEquals(productsPage.checkCounterValue(), 3);
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
