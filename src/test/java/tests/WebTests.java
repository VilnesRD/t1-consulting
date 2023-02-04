package tests;

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
    @ParameterizedTest
    @DisplayName("Проверка навигации по сайту")
    @Step
    public void headerTest(String headerData) {
        step ("Открываем главную страницу", () -> {
            open(baseUrl);
        });
        step ("Проверяем, что на странице есть кнопки из headerData", () -> {
            $("div.menu-top ul").shouldHave(text(headerData));;
        });
    }

    @ValueSource(strings = {"Пресс-центр", "Достижения", "Партнеры", "Группа компаний", "Комплаенс"})
    @ParameterizedTest
    @DisplayName("Проверка страницы 'О компании' на наличие кнопок из ValueSource")
    public void aboutUsTest(String aboutUsData) {
        step("Открываем страницу О компании", () -> {
            open(baseUrl + "about/");
        });
        step ("Проверяем, что на странице есть кнопки из aboutUsData", () -> {
            $("div.menu-inner ul").shouldHave(text(aboutUsData));
        });
    }

    @CsvFileSource(resources = "/testData/serviceData.csv")
    @ParameterizedTest
    @DisplayName("Проверка страницы 'Услуги' на наличие кнопок из CSV")
    public void servicesTest(String serviceData) {
        step("Открываем страницу Услуги", () -> {
            open(baseUrl + "/services/");
        });
        step ("Проверяем, что на странице есть кнопки из servicesData", () -> {
            $("ul.service-list").shouldHave(text(serviceData));
        });
    }

    @CsvFileSource(resources = "/testData/returnButton.csv")
    @ParameterizedTest
    @DisplayName("Проверка появления страницы 404")
    public void returnButton(String servicePage, String errorPage) {
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

}
