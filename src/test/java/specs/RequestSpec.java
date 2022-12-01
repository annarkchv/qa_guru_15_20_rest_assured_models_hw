package specs;

import io.restassured.specification.RequestSpecification;

import static helpers.CustomApiListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;

public class RequestSpec {
    public static RequestSpecification postRequestSpec = with()
            .filter(withCustomTemplates())
            .contentType(JSON);

    public static RequestSpecification getRequestSpec = with()
            .filter(withCustomTemplates());
}
