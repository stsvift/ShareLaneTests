
package ru.volkov.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class ZipCodePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By zipInput = By.name("zip_code");
    private final By continueButton =
            By.cssSelector("input[value='Continue']");

    public ZipCodePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );
    }

    public void enterZip(String zip) {
        WebElement input = wait.until(
                ExpectedConditions.visibilityOfElementLocated(zipInput)
        );

        input.clear();
        input.sendKeys(zip);
        wait.until(
                ExpectedConditions.elementToBeClickable(continueButton)
        ).click();
    }

    public boolean isZipVisible() {
        return !driver.findElements(zipInput).isEmpty();
    }

    public String getPageText() {
        return driver.findElement(By.tagName("body")).getText();
    }
}
