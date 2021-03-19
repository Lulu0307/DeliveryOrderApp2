package ru.netology;

import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestRegistrationForm {

    public RegistrationData data = DataGenerator.generateData();
    private SelenideElement form;

    @BeforeEach
    void setUp() {
        open("http://localhost:9999");
        form = $("[id = root]");
        form.$("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
    }

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }


    @Test
    void shouldFillForm() {
        form.$("[data-test-id=city] input").setValue(data.getCity());
        form.$("[data-test-id=date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id=name] input").setValue(data.getName());
        form.$("[data-test-id=phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(data.getDeliveryDate())).waitUntil(visible, 15000);
    }

    @Test
    void shouldSuggestAnotherDate() {
        shouldFillForm();
        form.$(".button").click();
        $(withText("Перепланировать")).waitUntil(visible, 15000);
        $(byText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована")).waitUntil(visible, 15000);
    }

    @Test
    void shouldNotSendFormWithNotFullName() {
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue("Вася");
        form.$("[data-test-id = phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."))
                .shouldBe(visible);
    }

    @Test
    void shouldNotSendFormWithIncorrectName2() {
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue("----");
        form.$("[data-test-id = phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText("Имя и Фамилия указаны неверно. Допустимы только русские буквы, пробелы и дефисы."))
                .shouldBe(visible);
    }

    @Test
    void shouldNotSendFormWithIncorrectPhoneNumber() {
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue(data.getName());
        form.$("[data-test-id = phone] input").setValue("+7940");
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText("Ошибка")).shouldBe(visible);
    }

}