package petshop.spec;

import static io.restassured.RestAssured.given;

import io.restassured.response.ValidatableResponse;
import petshop.dto.UserDTO;
public class ResSpecification {
    public ValidatableResponse createUser(UserDTO userDTO){
        return  given(RegSpecification.requestSpec_Create)
                .body(userDTO)
                .when()
                .post()
                .then().log().all();
    }
    public ValidatableResponse getUser(){
        return  given(RegSpecification.requestSpecGet)
                .when()
                .get()
                .then().log().all();
    }
    public ValidatableResponse getUserInvalid(){
        return  given(RegSpecification.requestSpecGet)
                .when()
                .post()
                .then().log().all();
    }
}
