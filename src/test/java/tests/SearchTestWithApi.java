package tests;

import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.RestAssured.given;


public class SearchTestWithApi {
    @Test
    public void successfulSearch() {
        Response response = given()
                .get("https://www.t1-consulting.ru/search/?q=T1")
                .then()
                .log().body()
                .extract()
                .response();
        String responseBody = response.getBody().asString();

        assertThat(responseBody, containsString("T1"));
    }

    @Test
    public void unSuccessfulSearch() {
        Response response = given()
                .get("https://www.t1-consulting.ru/search/?q=123123131")
                .then()
                .log().body()
                .extract()
                .response();
        String responseBody = response.getBody().asString();

        assertThat(responseBody, containsString("К сожалению, на ваш поисковый запрос ничего не найдено."));
    }
}
