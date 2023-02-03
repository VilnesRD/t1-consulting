package tests;



import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiTest {

    @Test
    @DisplayName("Проверка работы поиска")
    void searchTest (){
        given()
                .cookie("_ga","GA1.2.814132539.1674534219")
                .when()
                .get("https://www.t1-consulting.ru/search/?q=%D2%E5%F1%F2")
                .then()
                .statusCode(200)
                .log().body()
                .body("/html/body/div[1]/div/div[2]/div[2]/div[2]/ul/li[1]/div/p/b",
                        equalTo("Тест"));
    }



//        step("Открываем главную страницу", () -> {
//            open(baseUrl);
//        });
//        step("Проверяем, что на странице есть поле поиска", () -> {
//            $(".but-s").shouldBe(visible, Duration.ofSeconds(5));
//            $(".but-s").click();
//        });
//        step("Вводим в поле поиска 'Тестирование'", () -> {
//            $(".field-s").shouldBe(visible, Duration.ofSeconds(5)).click();
//            $(".field-s input").setValue("Тестирование").pressEnter();
//        });
//        step("Проверяем, что на странице есть результаты поиска", () -> {
//            $$("ul.list-achiement li").get(0).shouldHave(text("Тестирование"));
//        });
//    }
}
