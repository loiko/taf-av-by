package api;

import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VinNumberTest {
    private static final Logger logger = LogManager.getLogger();
    private static final String BASE_URL = "https://api.av.by/vin-reports/previews/autoteka/previews";
    private static final String VALID_VIN_NUMBER = "4JGCB75E66A021101";

    @Test
    public void testSearchAdByVinNumber() {
        String body = "{\n" +
                "    \"vin\": \"" + VALID_VIN_NUMBER + "\"\n" +
                "}";

        try {
            Response response = given().header("content-type", "application/json")
                    .body(body)
                    .when().post(BASE_URL)
                    .then().assertThat()
                    .extract().response();

            int previewId = response.path("previewId");

            given().header("content-type", "application/json")
                    .when().get(BASE_URL + "/" + previewId)
                    .then().assertThat()
                    .statusCode(200)
                    .body("previewInfo.vin", equalTo("4JGCB75E66A021101"))
                    .body("previewInfo.brand", equalTo("MERCEDES"))
                    .body("previewInfo.model", equalTo("R-KLASSE"))
                    .body("previewInfo.year", equalTo(2006));
            logger.info("Test 'testSearchAdByVinNumber' passed successfully");
        } catch (Exception e) {
            logger.error("Test 'testInvalidLoginAndPassword' failed: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCheckInvalidVinNumber() {
        String body = "{\n" +
                "    \"vin\": \"00000000000000000\"\n" +
                "}";

        try {
            given().header("content-type", "application/json")
                    .body(body)
                    .when().post(BASE_URL)
                    .then().assertThat()
                    .statusCode(417);
            logger.info("Test 'testCheckInvalidVinNumber' passed successfully");
        } catch (Exception e) {
            logger.error("Test 'testInvalidLoginAndPassword' failed: {}", e.getMessage());
            throw e;
        }
    }

    @Test
    public void testCheckEmptyVinNumber() {
        String body = "{\n" +
                "    \"vin\": \"\"\n" +
                "}";

        try {
            given().header("content-type", "application/json")
                    .body(body)
                    .when().post(BASE_URL)
                    .then().assertThat()
                    .statusCode(400)
                    .body("context.errors.vin[0]", equalTo("Неверно указан VIN-номер"));
            logger.info("Test 'testInvalidLoginAndPassword' passed successfully");
        } catch (Exception e) {
            logger.error("Test 'testInvalidLoginAndPassword' failed: {}", e.getMessage());
            throw e;
        }
    }
}
