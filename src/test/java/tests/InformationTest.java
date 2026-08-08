package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import user.UserInformation;

import static enums.TitleNaming.CHECKOUT2;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;
import static user.UserFactory.withCorrectData;

@Epic("Интернет-магазин")
@Feature("Заказ")
@Owner("Tamara Iutina uytinabp@gmail.com")
public class InformationTest extends BaseTest {

    @Story("Успешный ввод информации о покупателе для заказа")
    @Test()
    public void correctInformationTest() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.switchToCart();

        cartPage.checkOut();

        informationPage.data(withCorrectData());

        assertTrue(overviewPage.pageIsOpen());
        assertEquals(overviewPage.getNamePage(), CHECKOUT2.getDisplayName());
    }

    @Story("Неуспешный ввод информации о покупателе для заказа")
    @Test()
    public void inCorrectInformationTest() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        productsPage.addToCart("Sauce Labs Bike Light");
        productsPage.switchToCart();

        cartPage.checkOut();

        informationPage.data(new UserInformation("", "", ""));
        assertTrue(informationPage.isErrorDisplayed());
        assertEquals(informationPage.getErrorText(), "Error: First Name is required");
    }
}
