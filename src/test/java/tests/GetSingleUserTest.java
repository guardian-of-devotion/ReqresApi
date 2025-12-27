package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.SingleUserResponse;
import models.UserData;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GetSingleUserTest {
    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetSingleUser() throws Exception {
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Десериализация JSON-ответа в объект SingleUserResponse
        SingleUserResponse singleUserResponse = objectMapper.readValue(response.asString(), SingleUserResponse.class);

        // Переменная, хранящая пользователя
        UserData user = singleUserResponse.getData();

        // Проверяем, что email пользователя оканчивается на @reqres.in
            assertTrue(user.getEmail().endsWith("@reqres.in"), "Email пользователя " + user.getId() + " не оканчивается на @reqres.in");
        }
}
