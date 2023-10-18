package petshop.spec;

import static io.restassured.RestAssured.given;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import petshop.dto.UserDTO;
public class ResSpecification {
    @Step("Validate response CreateUser")
    public ValidatableResponse createUser(UserDTO userDTO){
        return  given(RegSpecification.requestSpec_Create)
                .body(userDTO)
                .when()
                .post()
                .then().log().all();
    }
    @Step("Validate response GetUser")
    public ValidatableResponse getUser(){
        return  given(RegSpecification.requestSpecGet)
                .when()
                .get()
                .then().log().all();
    }
    @Step("Validate response CreateUser with invalid parameters")
    public ValidatableResponse getUserInvalid(){
        return  given(RegSpecification.requestSpecGet)
                .when()
                .post()
                .then().log().all();
    }
}
