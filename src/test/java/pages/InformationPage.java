package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.UserInformation;

public class InformationPage extends BasePage {
    private final By firstNameInput = By.xpath("//input[@id='first-name']");
    private final By lastNameInput = By.xpath("//input[@id='last-name']");
    private final By postalCodeInput = By.xpath("//input[@id='postal-code']");
    private final By continueBtn = By.cssSelector("#continue");

    public InformationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Ввод информации о покупателе для заказа")
    public void data(UserInformation userInformation) {
        driver.findElement(firstNameInput).sendKeys(userInformation.getFirstName());
        driver.findElement(lastNameInput).sendKeys(userInformation.getLastName());
        driver.findElement(postalCodeInput).sendKeys(userInformation.getPostalCode());
        driver.findElement(continueBtn).click();
    }
}
