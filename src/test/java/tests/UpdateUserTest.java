package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserCredentials;
import models.UserModelResponse;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UpdateUserTest {
    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testUpdateUserPut() throws JsonProcessingException {
        UserCredentials user = new UserCredentials("morpheus");

        step("Отправляем PUT-запрос");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует updatedAt");
        assertNotNull(userModelResponse.getUpdatedAt(), "updatedAt не должен быть null");

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Работа пользователя не совпадает");

    }

    @Test
    public void testUpdateUserPatch() throws JsonProcessingException {
        UserCredentials user = new UserCredentials("morpheus");

        step("Отправляем PATCH-запрос");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .patch(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует updatedAt");
        assertNotNull(userModelResponse.getUpdatedAt(), "updatedAt не должен быть null");

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Работа пользователя не совпадает");
    }
}

