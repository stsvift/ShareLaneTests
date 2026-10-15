
package ru.volkov.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class SuccessPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public SuccessPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(
                driver, Duration.ofSeconds(15)
        );
    }

    public String getMessage() {
        return wait.until(d ->
                d.findElement(By.tagName("body")).getText()
                        .contains("Account is created!")
                        ? d.findElement(By.tagName("body")).getText()
                        : null
        );
    }

    public boolean isCreated() {
        return getMessage().contains("Account is created!");
    }
}
