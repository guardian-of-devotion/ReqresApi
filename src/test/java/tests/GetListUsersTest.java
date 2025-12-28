package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.qameta.allure.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.UserData;
import models.UsersResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Работа с данными пользователей")
@Feature("Получает информацию о пользователе")
public class GetListUsersTest {
    private final String BASE_URL = "https://reqres.in/api/users";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Story("Получение информации о списке пользователей")
    @Description("Получение информации о всех пользователях")
    @Severity(SeverityLevel.MINOR)
    @Test
    public void testGetListUsers() throws Exception {
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        // Десериализация JSON-ответа в объект UsersResponse
        UsersResponse usersResponse = objectMapper.readValue(response.asString(), UsersResponse.class);

        // Проверяем, что кол-во пользователей = 6
        assertEquals(6, usersResponse.getData().size(), "Количество пользователей не совпало с ожидаемым");
        assertEquals(1, usersResponse.getPage(), "Неверная страница");
        assertEquals(2, usersResponse.getTotal_pages(), "Неверное общее количество страниц");
        assertEquals(12, usersResponse.getTotal(), "Неверное количество страниц");

        for (UserData user : usersResponse.getData()) {
            assertTrue(user.getEmail().endsWith("@reqres.in"), "Email пользователя " + user.getId() + " не оканчивается на @reqres.in");
        }
    }
}