import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginTest {

    @Test
    public void login()  {
        WebDriver browser = new ChromeDriver();
        browser.get("https://www.saucedemo.com/");
        browser.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        browser.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        browser.findElement(By.cssSelector("#login-button")).click();
        assertTrue(browser.findElement(By.xpath("//span[@data-test='title']")).isDisplayed());
        assertEquals(browser.findElement(By.xpath("//span[@data-test='title']")).getText(), "Products");
        browser.quit();
    }
}
