package tests;

import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import org.testng.annotations.Test;

import java.util.List;

import static enums.TitleNaming.CART;
import static org.testng.Assert.*;
import static user.UserFactory.withAdminPermission;

public class CartTest extends BaseTest {
    List<String> goodList =
            List.of("Sauce Labs Bike Light",
                    "Sauce Labs Backpack",
                    "Sauce Labs Bolt T-Shirt");

    @Step("Проверяем отображение товаров в корзине")
    @Test
    @Owner("Tamara Iutina uytinabp@gmail.com")
    public void checkGoodsAdded() {
        System.out.println("CartTest.checkGoodsAdded running in thread: " + Thread.currentThread().getName());
        loginPage.open();
        loginPage.login(withAdminPermission());

        for (String goodName : goodList) {
            productsPage.addToCart(goodName);
        }
        productsPage.switchToCart();

        assertTrue(cartPage.pageIsOpen());
        assertEquals(cartPage.getNamePage(), CART.getDisplayName());

        assertFalse(cartPage.getProductsNames().isEmpty());
        assertEquals(cartPage.getProductsNames().size(), 3);
        assertTrue(cartPage.getProductsNames().contains("Sauce Labs Bolt T-Shirt"));
        assertEquals(cartPage.getProductsNames(), goodList);
    }
}
