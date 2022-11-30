package tests;

import models.LoginRequestLombokModel;
import models.LoginResponseLombokModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.LoginSpecs.loginRequestSpec;

public class LoginTestExtended extends TestBase {
    LoginRequestLombokModel loginCredentials = new LoginRequestLombokModel();

    @Test
    void loginSuccessful() {
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .spec(loginRequestSpec)
                .body(loginCredentials)
                .when()
                .post(login)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginUnsuccessful() {
        loginCredentials.setEmail("eve.holt@reqres.in");

        LoginResponseLombokModel response = given()
                .spec(loginRequestSpec)
                .body(loginCredentials)
                .when()
                .post(login)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .extract().as(LoginResponseLombokModel.class);

    }
}
