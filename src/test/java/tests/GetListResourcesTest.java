package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ResourceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetListResourcesTest {

    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetListResources() throws Exception {
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        ResourceResponse resourceResponse = objectMapper.readValue(response.asString(), ResourceResponse.class);

        assertEquals(1, resourceResponse.getPage(), "Неверная страница");
        assertEquals(6, resourceResponse.getData().size(), "Кол-во ресурсов не совпало с ожидаемым");
        assertEquals(12, resourceResponse.getTotal(), "Неверное кол-во страниц");
        assertEquals(2, resourceResponse.getTotal_pages(), "Неверное общее кол-во страниц");



    }
}
