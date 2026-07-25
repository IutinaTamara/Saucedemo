package tests;

import org.testng.annotations.Test;
import java.util.List;
import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {
    List<String> goodList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Backpack",
                    "Sauce Labs Bolt T-Shirt");

    @Test
    public void checkGoodsAdded() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        productsPage.pageIsOpen();
        for (String goodName : goodList) {
            productsPage.addToCart(goodName);
        }

        assertEquals(productsPage.checkCounterValue(), 3);
        assertEquals(productsPage.checkCounterColor(), "rgba(226, 35, 26, 1)");
    }
}
