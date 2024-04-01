package api;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoginTest {
    private static final String BASE_URL = "https://api.av.by/auth/login/sign-in";

    @Test
    public void testInvalidLoginAndPassword() {
        String body = "{\n" +
                "    \"login\": \"1nvalidLogin\",\n" +
                "    \"password\": \"1Nv@lidPassw0rd\"\n" +
                "}";

        given().header("content-type", "application/json")
                .body(body)
                .when().post(BASE_URL)
                .then().assertThat()
                .statusCode(400)
                .body("messageText", equalTo("Неверный логин или пароль. Если забыли пароль, восстановите его"));
    }
}
