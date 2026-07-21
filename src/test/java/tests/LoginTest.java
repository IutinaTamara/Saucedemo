package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    public void correctLogin() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        assertTrue(driver.findElement(By.xpath("//span[@data-test='title']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//span[@data-test='title']")).getText(), "Products");
    }

    @Test
    public void incorrectLogin() {
        loginPage.open();
        loginPage.login("Standard_user", "secret_sauce");

        assertTrue(driver.findElement(By.xpath("//button[@data-test='error-button']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).getText(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void emptyPassword() {
        loginPage.open();
        loginPage.login("Standard_user", "");

        assertTrue(driver.findElement(By.xpath("//button[@data-test='error-button']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).getText(), "Epic sadface: Password is required");
    }

    @Test
    public void emptyLogin() {
        loginPage.open();
        loginPage.login("", "secret_sauce");

        assertTrue(driver.findElement(By.xpath("//button[@data-test='error-button']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).getText(), "Epic sadface: Username is required");
    }

    @Test
    public void lockedLogin() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        assertTrue(driver.findElement(By.xpath("//button[@data-test='error-button']")).isDisplayed());
        assertEquals(driver.findElement(By.xpath("//h3[@data-test='error']")).getText(), "Epic sadface: Sorry, this user has been locked out.");
    }
}
