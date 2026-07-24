package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static pages.BasePage.BASE_URL;

public class LoginPage {
    private final By loginInput = By.xpath("//input[@id='user-name']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginBtn = By.cssSelector("#login-button");
    private final By error = By.xpath("//div[@class='error-message-container error']");
    private final By errorText = By.xpath("//h3[@data-test='error']");

    WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(final String userName, final String password) {
        driver.findElement(loginInput).sendKeys(userName);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginBtn).click();
    }

    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    public String getErrorText() {
        return driver.findElement(errorText).getText();
    }
}
