package tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import models.UserCredentials;
import models.UserModelResponse;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Epic("Создание пользователя")
@Feature("Создает нового пользователя")
public class CreateUserTest {

    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Создание пользователя без работы")
    @Description("Создается пользователь только с именем, без работы")
    @Test
    public void testCreateUserWithoutJob() throws JsonProcessingException {
        UserCredentials user = new UserCredentials("morpheus");

        step("Отправляем POST-запрос");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();

        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Профессия пользователя не совпадает");
    }

    @Story("Создание пользователя без имени")
    @Description("Создается пользователь только с работой, без имени")
    @Test
    public void testCreateUserWithoutName() throws JsonProcessingException {
        UserCredentials user = new UserCredentials("", "programmer");

        step("Отправляем POST-запрос");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();

        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Профессия пользователя не совпадает");
    }

    @Story("Создание пользователя с работой и именем")
    @Description("Создается пользователь с полными данными: работа и имя")
    @Test
    public void testCreateUserWithNameAndJob() throws JsonProcessingException {
        UserCredentials user = new UserCredentials("morpheus", "programmer");

        step("Отправляем POST-запрос");
        Response response = RestAssured
                .given()
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .post(BASE_URL)
                .then()
                .statusCode(201)
                .extract()
                .response();

        step("Десериализация JSON-ответа в объект UserModelResponse");
        UserModelResponse userModelResponse = objectMapper.readValue(response.asString(), UserModelResponse.class);

        step("Проверяем, что в ответе присутствует id и createdAt");
        assertNotNull(userModelResponse.getId(), "ID не должен быть null");
        assertNotNull(userModelResponse.getCreatedAt(), "createdAt не должен быть null");

        step("Проверяем, что имя и работа совпадают с теми, что были отправлены");
        assertEquals(user.getName(), userModelResponse.getName(), "Имя пользователя не совпадает");
        assertEquals(user.getJob(), userModelResponse.getJob(), "Профессия пользователя не совпадает");
    }
}


