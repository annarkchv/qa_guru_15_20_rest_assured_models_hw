package tests;

import models.RequestModel;
import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

import static org.assertj.core.api.Assertions.assertThat;
import static specs.RequestSpec.postRequestSpec;

public class LoginTest extends TestBase {
    RequestModel loginCredentials = new RequestModel();

    @Test
    void loginSuccessful() {
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        ResponseModel response = given()
                .spec(postRequestSpec)
                .body(loginCredentials)
                .when()
                .post(login)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(ResponseModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginUnsuccessful() {
        loginCredentials.setEmail("eve.holt@reqres.in");

        ResponseModel response = given()
                .spec(postRequestSpec)
                .body(loginCredentials)
                .when()
                .post(login)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(ResponseModel.class);

        assertThat(response.getError()).isEqualTo("Missing password");
    }
}
