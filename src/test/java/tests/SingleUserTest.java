package tests;

import models.RequestModel;
import models.ResponseModel;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static specs.RequestSpec.getRequestSpec;
import static specs.RequestSpec.postRequestSpec;

public class SingleUserTest extends TestBase {
    RequestModel userData = new RequestModel();

    @Test
    void singleUserInfo() {
        given()
                .spec(getRequestSpec)
                .get(singleUser)
                .then()
                .log().body()
                .body("data.id", is(2))
                .body("data.email", is("janet.weaver@reqres.in"))
                .body("data.first_name", is("Janet"))
                .body("data.last_name", is("Weaver"));
    }

    @Test
    void createUser() {
        userData.setName("Jane Doe");
        userData.setJob("QA Engineer");

        ResponseModel response = given()
                .spec(postRequestSpec)
                .body(userData)
                .when()
                .post(createUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .extract().as(ResponseModel.class);

        assertThat(response.getName()).isEqualTo(userData.getName());
        assertThat(response.getJob()).isEqualTo(userData.getJob());
    }

    @Test
    void updateUser() {
        userData.setName("Jane Doe");
        userData.setJob("QA Engineer");

        ResponseModel response = given()
                .spec(postRequestSpec)
                .body(userData)
                .when()
                .put(singleUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().as(ResponseModel.class);

        assertThat(response.getName()).isEqualTo(userData.getName());
        assertThat(response.getJob()).isEqualTo(userData.getJob());
    }

    @Test
    void deleteUser() {
        given()
                .spec(getRequestSpec)
                .delete(singleUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(204);
    }
}
