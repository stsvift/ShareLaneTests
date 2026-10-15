
package ru.volkov.tests;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import ru.volkov.BaseTest;
import ru.volkov.pages.*;

import static org.junit.jupiter.api.Assertions.*;

public class RegistrationTest extends BaseTest {

    private final String errorZip =
            "ZIP code should have 5 digits";

    private final String errorFields =
            "Some of your fields have invalid data";

    private RegistrationPage openRegistration() {
        ZipCodePage zipPage = new ZipCodePage(driver);
        zipPage.enterZip("12345");

        return new RegistrationPage(driver);
    }

    @Test
    void validZipTest() {
        ZipCodePage page = new ZipCodePage(driver);

        page.enterZip("12345");

        RegistrationPage registration =
                new RegistrationPage(driver);

        assertTrue(
                registration.isFormVisible(),
                "Форма регистрации не появилась"
        );
    }

    @Test
    void invalidZipTest() {
        ZipCodePage page = new ZipCodePage(driver);

        page.enterZip("abcde");

        assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"), errorZip
                )),
                "Не появилась ошибка ZIP-кода"
        );

        assertTrue(page.isZipVisible());
    }

    @Test
    void emptyZipTest() {
        ZipCodePage page = new ZipCodePage(driver);

        page.enterZip("");

        assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"), errorZip
                )),
                "Не появилась ошибка пустого ZIP-кода"
        );
    }

    @Test
    void emptyFirstNameTest() {
        RegistrationPage page = openRegistration();

        page.register(
                "",
                "Volkov",
                "stepan@example.com",
                "1234",
                "1234"
        );

        assertTrue(
                wait.until(ExpectedConditions.textToBePresentInElementLocated(
                        By.tagName("body"), errorFields
                )),
                "Регистрация с пустым именем не отклонена"
        );

        assertTrue(page.isFormVisible());
    }

    @Test
    void successfulRegistrationTest() {
        RegistrationPage page = openRegistration();

        String email = "stepan" +
                java.util.UUID.randomUUID()
                        .toString().substring(0, 8) +
                "@example.com";

        page.register(
                "Stepan",
                "Volkov",
                email,
                "1234",
                "1234"
        );

        SuccessPage success = new SuccessPage(driver);

        assertTrue(
                success.isCreated(),
                "Регистрация не была завершена"
        );
    }

}
