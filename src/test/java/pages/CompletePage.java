package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CompletePage extends BasePage {
    private final By imgSuccess = By.cssSelector(DATA_TEST_PATTERN.formatted("pony-express"));

    public CompletePage(WebDriver driver) {
        super(driver);
    }

    @Step("Отображение иконки успеха для заказа")
    public boolean getDisplayImgComplete() {
        return driver.findElement(imgSuccess).isDisplayed();
    }
}
