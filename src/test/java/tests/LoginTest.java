package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test(description = "Проверка успешной авторизации")
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(basePage.pageIsOpen());
        assertEquals(productsPage.getNamePage(), "Products");
    }

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("Standard_user", "");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Password is required");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Username is required");
    }

    @Test
    public void lockedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(loginPage.isErrorDisplayed());
        assertEquals(loginPage.getErrorText(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
