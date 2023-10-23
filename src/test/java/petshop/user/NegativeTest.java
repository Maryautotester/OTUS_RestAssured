package petshop.user;

import assertation.Assert;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import petshop.dto.GetUserResponseDTO;
import petshop.spec.RegSpecification;
import petshop.spec.ResSpecification;

public class NegativeTest {
    /*
    Test check error "User not found" for user101.
    Status code is 404, Status Line is "HTTP/1.1 404 Not Found",
    code 1, type is error, message is User not found.
     */
    @Test
    @DisplayName("Проверить наличие ошибки \"User not found\" для user101")
    public void getUserByName_User1() {
        RegSpecification reqspec = new RegSpecification();
        reqspec.regSpecificationGet("user901");
        ValidatableResponse response = new ResSpecification().getUser();
        GetUserResponseDTO getUser = response.extract().body().as(GetUserResponseDTO.class);


        //        response.statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        Assert.assertStatusCode(response, 404, "HTTP/1.1 404 Not Found");
        Assert.assertError(getUser, 1, "error", "User not found");
    }
    /*
    Test check this endpoint couldn't be used with POST method.
     */
    @Test
    @DisplayName("Проверить, что endpoint /user/{username} не используется с методом POST")
    public void getUserByName_InvalidUserName() {
        RegSpecification reqspec = new RegSpecification();
        reqspec.regSpecificationGet("123");
        Assert.assertStatusCode(new ResSpecification().getUserInvalid(), 405, "HTTP/1.1 405 Method Not Allowed");
        //        new ResSpecification().getUserInvalid().statusCode(405).statusLine("HTTP/1.1 405 Method Not Allowed");
    }
}
