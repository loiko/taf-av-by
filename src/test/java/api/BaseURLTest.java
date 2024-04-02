package api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;

public class BaseURLTest {
    private static final Logger logger = LogManager.getLogger();

    @Test
    public void testBaseURL() {
        try {
            when().get("https://av.by/").then().statusCode(200);
            logger.info("Base URL test passed successfully");
        } catch (AssertionError e) {
            logger.error("Base URL test failed: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred during the test: {}", e.getMessage());
            throw e;
        }
    }
}
