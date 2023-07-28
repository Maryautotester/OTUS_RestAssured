package petshop.spec;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RegSpecification {
    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static String BASE_PATH = "/user";

    public static RequestSpecification requestSpec_Create = given()
            .baseUri(BASE_URI)
            .basePath(BASE_PATH)
            .contentType(ContentType.JSON)
            .log().all();
    static RequestSpecification requestSpecGet;
    public RequestSpecification regSpecificationGet(String userName){
        BASE_PATH = "/user/{username}";
        return requestSpecGet = given()
                .pathParam("username", userName)
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .log().all();
    }
}