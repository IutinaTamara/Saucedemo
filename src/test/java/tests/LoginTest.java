package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import utils.PropertyReader;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static user.UserFactory.withAdminPermission;

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
        String userCorrect = PropertyReader.getProperty("saucedemo.user");
        String userIncorrect = PropertyReader.getProperty("saucedemo.incorrect_user");
        String userLocked = PropertyReader.getProperty("saucedemo.locked_user");
        String password = PropertyReader.getProperty("saucedemo.password");

        return new Object[][]{
                {userIncorrect, password, "Epic sadface: Username and password do not match any user in this service"},
                {userCorrect, "", "Epic sadface: Password is required"},
                {"", password, "Epic sadface: Username is required"},
                {userLocked, password, "Epic sadface: Sorry, this user has been locked out."}
        };
    }

    @Test(dataProvider = "loginData", description = "Проверка ошибок при неверной авторизации")
    public void incorrectLogin(String login, String password, String errorMsg) {
        User user = new User(login, password);
        loginPage.open();
        loginPage.login(user);

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), errorMsg);
    }
}
