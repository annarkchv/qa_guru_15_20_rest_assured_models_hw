package tests;

import models.RequestModel;
import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static specs.RequestSpec.postRequestSpec;

public class RegisterTest extends TestBase {

    RequestModel registerCredentials = new RequestModel();

    @Test
    void registerSuccessful() {
        registerCredentials.setEmail("eve.holt@reqres.in");
        registerCredentials.setPassword("cityslicka");

        ResponseModel response = given()
                .spec(postRequestSpec)
                .body(registerCredentials)
                .when()
                .post(register)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(ResponseModel.class);

        assertThat(response.getId()).isEqualTo(4);
    }

    @Test
    void registerUnsuccessful() {
        registerCredentials.setEmail("eve.holt@reqres.in");

        ResponseModel response = given()
                .spec(postRequestSpec)
                .body(registerCredentials)
                .when()
                .post(register)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(ResponseModel.class);

        assertThat(response.getError()).isEqualTo("Missing password");
    }
}
