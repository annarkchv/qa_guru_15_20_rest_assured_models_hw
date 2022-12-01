package tests;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static specs.RequestSpec.getRequestSpec;

public class ListUsersTest extends TestBase {

    @Test
    void listUsersTotal() {
        given()
                .spec(getRequestSpec)
                .get(listUsers)
                .then()
                .log().body()
                .body("total", is(12));
    }

    @Test
    void listUsersStatus() {
        given()
                .spec(getRequestSpec)
                .get(listUsers)
                .then() // проверка результата
                .log().status()
                .statusCode(200);
    }

    @Test
    void listUsersIdValues() {
        given()
                .spec(getRequestSpec)
                .get(listUsers)
                .then()
                .body("data.id", hasItems(7, 8, 9, 10, 11, 12));
    }

    @Test
    void listUsersHasSpecificId() {
        given()
                .spec(getRequestSpec)
                .get(listUsers)
                .then()
                .body("data.id", hasItem(12));
    }
}
