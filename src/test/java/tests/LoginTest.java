package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.*;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка успешной авторизации")
    public void correctLogin() {
        loginPage.open();
        loginPage.login(withAdminPermission());

        assertTrue(productsPage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), "Products");
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

    @Test(dataProvider = "loginData", description = "Проверка ошибок при неверной авторизации")
    public void incorrectLogin(User user, String errorMsg) {
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
