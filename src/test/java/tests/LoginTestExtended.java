package tests;

import io.qameta.allure.restassured.AllureRestAssured;
import models.lombok.LoginRequestLombokModel;
import models.lombok.LoginResponseLombokModel;
import models.pojo.LoginRequestPojoModel;
import models.pojo.LoginResponsePojoModel;
import org.junit.jupiter.api.Test;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static specs.LoginSpecs.loginRequestSpec;
import static specs.LoginSpecs.loginResponseSpec;

public class LoginTestExtended {

    @Test
    void loginPojoModelTest() {
//        String loginCredentials = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
//        Данные из строки выше задаются через LoginRequestPojoModel

        LoginRequestPojoModel loginCredentials = new LoginRequestPojoModel();
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        LoginResponsePojoModel response = given()
                .contentType(JSON)
                .body(loginCredentials)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                //.body("token", is("QpwL5tke4Pnpja7X4")); - вместо этой строки - строки ниже
                .extract().as(LoginResponsePojoModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginLombokModelTest() {
//        String loginCredentials = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\" }";
//        Данные из строки выше задаются через LoginRequestLombokModel

        LoginRequestLombokModel loginCredentials = new LoginRequestLombokModel();
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .contentType(JSON)
                .body(loginCredentials)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                //.body("token", is("QpwL5tke4Pnpja7X4")); - вместо этой строки - строки ниже
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginAllureListenerTest() {
        LoginRequestLombokModel loginCredentials = new LoginRequestLombokModel();
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(new AllureRestAssured()) // добавилась одна строка
                .contentType(JSON)
                .body(loginCredentials)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginCustomAllureListenerTest() {
        LoginRequestLombokModel loginCredentials = new LoginRequestLombokModel();
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        LoginResponseLombokModel response = given()
                .filter(withCustomTemplates()) // добавилась одна строка
                .contentType(JSON)
                .body(loginCredentials)
                .when()
                .post("https://reqres.in/api/login")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }

    @Test
    void loginSpecsTest() {
        LoginRequestLombokModel loginCredentials = new LoginRequestLombokModel();
        loginCredentials.setEmail("eve.holt@reqres.in");
        loginCredentials.setPassword("cityslicka");

        LoginResponseLombokModel response = given() // в given можно положить спек - given(loginRequestSpec)
                // filter и contentType вынесены в LoginSpec
                .spec(loginRequestSpec) // или спек можно добавить отдельной строкой
                .body(loginCredentials)
                .when()
                .post() // URI вынесен в LoginSpec
                .then()
                .spec(loginResponseSpec) // логирование и проверка статуса вынесены в спек
                .extract().as(LoginResponseLombokModel.class);

        assertThat(response.getToken()).isEqualTo("QpwL5tke4Pnpja7X4");
    }
}
