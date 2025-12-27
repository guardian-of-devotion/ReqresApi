package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResourceNotFoundTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";

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
