package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OverviewPage extends BasePage {
    private final By finishBtn = By.cssSelector("#finish");

    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    @Step("Подтверждение заказа")
    public void switchToComplete() {
        driver.findElement(finishBtn).click();
    }
}
