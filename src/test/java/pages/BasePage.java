package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("saucedemo.url");
    public static final String DATA_TEST_PATTERN = "[data-test='%s']";
    private final By error = By.xpath("//div[@class='error-message-container error']");
    private final By errorText = By.cssSelector(DATA_TEST_PATTERN.formatted("error"));
    private final By pageName = By.cssSelector(DATA_TEST_PATTERN.formatted("title"));

    WebDriver driver;
    WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @Step("Открытие страницы")
    public boolean pageIsOpen() {
        return driver.findElement(pageName).isDisplayed();
    }

    @Step("Получение названия страницы")
    public String getNamePage() {
        return driver.findElement(pageName).getText();
    }

    @Step("Проверяем отображается ли сообщение об ошибке")
    public boolean isErrorDisplayed() {
        return driver.findElement(error).isDisplayed();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String getErrorText() {
        return driver.findElement(errorText).getText();
    }

    @Step("Отмена заказа")
    public void switchToProducts(final String selectorName) {
        By selector = By.cssSelector(selectorName);
        driver.findElement(selector).click();
    }
}
