package PetShop;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class CreateUserApi {

    private static final String BASE_URI = "https://petstore.swagger.io/v2";
    private static final String BASE_PATH = "/user";
    private RequestSpecification spec;
    private RequestSpecification spec_get;
    public CreateUserApi(){
        spec = given()
                .baseUri(BASE_URI)
                .basePath(BASE_PATH)
                .contentType(ContentType.JSON)
                .log().all();
    }
    public ValidatableResponse createUser(UserDTO userDTO){
        return  given(spec)
                .body(userDTO)
                .when()
                    .post()
                .then().log().all();
    }

}