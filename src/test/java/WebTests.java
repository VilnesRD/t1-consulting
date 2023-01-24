import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.url;
import static io.qameta.allure.Allure.step;

public class WebTests extends TestBase {
    @ValueSource(strings = {"О компании", "Услуги", "Продукты", "Контакты"})
    @ParameterizedTest (name = "Проверка навигации по сайту")
    @Step
    void headerTest(String headerData) {
        step ("Открываем главную страницу", () -> {
            open(baseUrl);
        });
        step ("Проверяем, что на странице есть кнопки из headerData", () -> {
            $("div.menu-top ul").shouldHave(text(headerData));;
        });
    }


    @ValueSource(strings = {"Пресс-центр", "Достижения", "Партнеры", "Группа компаний", "Комплаенс"})
    @ParameterizedTest (name = "Проверяем, что на странице О компании есть кнопки перечисленные в ValueSource")
    void aboutUsTest(String aboutUsData) {
        step("Открываем страницу О компании", () -> {
            open(baseUrl + "about/");
        });
        step ("Проверяем, что на странице есть кнопки из aboutUsData", () -> {
            $("div.menu-inner ul").shouldHave(text(aboutUsData));
        });
    }

    @CsvFileSource(resources = "serviceData.csv")
    @ParameterizedTest (name = "Проверка навигации по странице Услуги")
    void servicesTest(String serviceData) {
        step("Открываем страницу Услуги", () -> {
            open(baseUrl + "services/");
        });
        step ("Проверяем, что на странице есть кнопки из servicesData", () -> {
            $("ul.service-list").shouldHave(text(serviceData));
        });
    }


    @CsvFileSource(resources = "returnButton.csv")
    @ParameterizedTest (name = "Проверка появления страницы 404 при переходе по гиперссылке 'Вернуться назад'")

    void returnButton(String servicePage, String errorPage) {
        step("Открываем страницу продукта {0}", () -> {
            open(baseUrl + servicePage);
        });
        step ("Проверяем, что на странице есть гиперссылка 'Вернуться назад'", () -> {
            $(".back").shouldBe(visible,text("Вернуться назад"));
        });
        step("Нажимаем на гиперссылку 'Вернуться назад'", () -> {
            $(".back").click();
        });
        step("Проверяем, что попали на страницу 404", () -> {
            Assertions.assertEquals(baseUrl + errorPage, url());
        });
    }

    @Test
    @DisplayName("Проверка работы поиска")
    void searchTest (){
        step("Открываем главную страницу", () -> {
            open(baseUrl);
        });
        step("Проверяем, что на странице есть поле поиска", () -> {
            $(".but-s").shouldBe(visible, Duration.ofSeconds(5));
            $(".but-s").click();
        });
        step("Вводим в поле поиска 'Тестирование'", () -> {
            $(".field-s").shouldBe(visible, Duration.ofSeconds(5)).click();
            $(".field-s input").setValue("Тестирование").pressEnter();
        });
        step("Проверяем, что на странице есть результаты поиска", () -> {
            $(".list-achiement").shouldBe(visible);
        });
    }

}
