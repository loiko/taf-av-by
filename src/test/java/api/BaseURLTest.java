package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class BaseURLTest {
    @Test
    public void testBaseURL() {
        when().get("https://av.by/").then().statusCode(200);
    }
}
