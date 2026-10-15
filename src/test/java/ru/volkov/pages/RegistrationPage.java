
package ru.volkov.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class RegistrationPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By firstName = By.name("first_name");
    private final By lastName = By.name("last_name");
    private final By email = By.name("email");
    private final By password = By.name("password1");
    private final By confirmPassword = By.name("password2");

    private final By registerButton =
            By.cssSelector("input[value='Register']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );
        wait.until(
                ExpectedConditions.visibilityOfElementLocated(firstName)
        );
    }

    private void fill(By locator, String value) {
        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
        element.clear();
        element.sendKeys(value);
    }

    public void register(String first, String last,
                         String mail, String pass, String confirm) {

        fill(firstName, first);
        fill(lastName, last);
        fill(email, mail);
        fill(password, pass);
        fill(confirmPassword, confirm);

        wait.until(
                ExpectedConditions.elementToBeClickable(registerButton)
        ).click();
    }

    public String getPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public boolean isFormVisible() {
        return !driver.findElements(firstName).isEmpty();
    }
}
