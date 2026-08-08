package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By loginInput = By.xpath("//input[@id='user-name']");
    private final By passwordInput = By.xpath("//input[@id='password']");
    private final By loginBtn = By.cssSelector("#login-button");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие браузера на странице авторизации")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Ввод логина: {user.login} и пароля: {user.password}")
    public void login(User user) {
        driver.findElement(loginInput).sendKeys(user.getLogin());
        driver.findElement(passwordInput).sendKeys(user.getPassword());
        driver.findElement(loginBtn).click();
    }
}
