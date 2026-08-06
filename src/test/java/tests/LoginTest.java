package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static enums.TitleNaming.PRODUCTS;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

@Epic("Интернет-магазин")
@Feature("Авторизация")
@Owner("Tamara Iutina uytinabp@gmail.com")
public class LoginTest extends BaseTest {

    @Story("Успешная авторизация")
    @Test(description = "Проверка успешной авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("my/courses.php")
    @Issue("course/view.php?id=100")
    public void correctLogin() {
        System.out.println("LoginTest.correctLogin running in thread: " + Thread.currentThread().getName());
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), PRODUCTS.getDisplayName());
    }

    @DataProvider()
    public Object[][] loginData() {

        return new Object[][]{
                {withIncorrectAdminPermission(), "Epic sadface: Username and password do not match any user in this service"},
                {new User("standard_user", ""), "Epic sadface: Password is required"},
                {new User("", "secret_sauce"), "Epic sadface: Username is required"},
                {withLockedAdminPermission(), "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Story("Неуспешная авторизация")
    @Test(dataProvider = "loginData", description = "Проверка ошибок при неверной авторизации")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("my/courses.php")
    @Issue("course/view.php?id=100")
    public void incorrectLogin(User user, String errorMsg) {
        System.out.println("LoginTest.incorrectLogin running in thread: " + Thread.currentThread().getName());
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
