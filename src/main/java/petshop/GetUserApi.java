package petshop;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class GetUserApi {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/user/";

    private final RequestSpecification spec;
    public GetUserApi(String userName){
        spec = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH + userName)
                .contentType(ContentType.JSON)
                .log().all();
    }
    public ValidatableResponse getUser(){
        return  given(spec)
                .when()
                .get()
                .then().log().all();
    }
    public ValidatableResponse getUserInvalid(){
        return  given(spec)
                .when()
                    .post()
                .then().log().all();
    }
}
