package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Epic("Работа с информацией о ресурсах")
public class ResourceNotFoundTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";

    @Story("Проверка получения данных несуществующего ресурса")
    @Test
    public void testResourceNotFound() throws Exception {
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/9999")
                .then()
                .statusCode(404)
                .extract()
                .response();

        String responseBody = response.getBody().asString();
        assertEquals("{}", "Тело ответа не является пустым");
    }

}
