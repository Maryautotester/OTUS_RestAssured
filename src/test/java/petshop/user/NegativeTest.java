package petshop.user;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
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
        reqspec.regSpecificationGet("user101");
        ValidatableResponse response = new ResSpecification().getUser();
        GetUserResponseDTO getUser = response.extract().body().as(GetUserResponseDTO.class);

        response.statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        Assertions.assertEquals(1, getUser.getCode(), "code is not 1");
        Assertions.assertEquals("error", getUser.getType(), "type is not error");
        Assertions.assertEquals("User not found", getUser.getMessage(), "message is not error");
    }
    /*
    Test check this endpoint couldn't be used with POST method.
     */
    @Test
    @DisplayName("Проверить, что endpoint /user/{username} не используется с методом POST")
    public void getUserByName_InvalidUserName() {
        RegSpecification reqspec = new RegSpecification();
        reqspec.regSpecificationGet("123");
        new ResSpecification().getUserInvalid().statusCode(405).statusLine("HTTP/1.1 405 Method Not Allowed");
    }
}
