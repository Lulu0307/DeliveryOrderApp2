package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestRegistrationForm {

    public RegistrationData data = DataGenerator.generateData();

    void setUp() {
        open("http://localhost:9999");

    }

    @Test
    void shouldFillForm() {
        setUp();
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id=city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id=date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id=name] input").setValue(data.getName());
        form.$("[data-test-id=phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(data.getDeliveryDate())).waitUntil(visible, 15000);
    }


    @Test
    void shouldSuggestAnotherDate() {
        setUp();
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue(data.getName());
        form.$("[data-test-id = phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(data.getDeliveryDate())).waitUntil(visible, 15000);
        form.$(".button").click();
        $(withText("Перепланировать")).waitUntil(visible, 15000);
        $(byText("Перепланировать")).click();
        $(withText("Встреча успешно запланирована")).waitUntil(visible, 15000);
    }

    @Test
    void sendFormWithNotFullName() {
        setUp();
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue("Вася");
        form.$("[data-test-id = phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(data.getDeliveryDate())).waitUntil(visible, 15000);
    }

    @Test
    void sendFormWithIncorrectName2() {
        setUp();
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue("----");
        form.$("[data-test-id = phone] input").setValue(data.getPhone());
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(data.getDeliveryDate())).waitUntil(visible, 15000);
    }

    @Test
    void sendFormWithIncorrectPhoneNumber() {
        setUp();
        SelenideElement form = $("[id = root]");
        form.$("[data-test-id = city] input").setValue(data.getCity());
        form.$("[data-test-id = date] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        form.$("[data-test-id = date] input").setValue(data.getDeliveryDate());
        form.$("[data-test-id = name] input").setValue(data.getName());
        form.$("[data-test-id = phone] input").setValue("+7940");
        form.$("[data-test-id = agreement]").click();
        form.$(".button").click();
        $(withText(data.getDeliveryDate())).waitUntil(visible, 15000);
    }

}