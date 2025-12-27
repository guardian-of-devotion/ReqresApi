package tests;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.ResourceData;
import models.SingleResourceResponse;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetSingleResourceTest {
    private final String BASE_URL = "https://reqres.in/api/unknown";
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void testGetSingleResource() throws Exception {
        Response response = RestAssured
                .given()
                .when()
                .get(BASE_URL + "/2")
                .then()
                .statusCode(200)
                .extract()
                .response();

        SingleResourceResponse singleResourceResponse = objectMapper.readValue(response.asString(), SingleResourceResponse.class);

        ResourceData resource = singleResourceResponse.getData();

        assertEquals(2, resource.getId(), "ID некорректный");
        assertEquals("fuchsia rose", resource.getName(), "Название некорректное");
        assertEquals(2001, resource.getYear(), "Год некорректный");
        assertEquals("#C74375", resource.getColor(), "Некорректный цвет");
        assertEquals("17-2031", resource.getPantone_value(), "Некорректный оттенок");
    }
}
