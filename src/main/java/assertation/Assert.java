package assertation;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import petshop.dto.GetUserResponseDTO;
import petshop.dto.GetUserResponseFullDTO;
import petshop.dto.UserDTO;


public class Assert {

    @Step("Validation status code response")
    public static void assertStatusCode(ValidatableResponse response, int statusCode, String statusLine) {
        response.statusCode(statusCode).statusLine(statusLine);

    }

    @Step("Validation error for response")
    public static void assertError(GetUserResponseDTO getUser, int code, String type, String message){
        Assertions.assertEquals(code, getUser.getCode(), "code is not 1");
        Assertions.assertEquals(type, getUser.getType(), "type is not error");
        Assertions.assertEquals(message, getUser.getMessage(), "message is not error");
    }

    @Step("Validate user's fields")
    public static void assertUserRes(UserDTO userDTO, Long userId, String userName, String firstName, String lastName,
                                     String email, String phone, String pwd, Long userStatus){

        Assertions.assertEquals(userId, userDTO.getId(), "Incorrect ID");
        Assertions.assertEquals(userName, userDTO.getUsername(), "Incorrect Username");
        if(firstName == null) {
            Assertions.assertNull(userDTO.getFirstName(), "FirstName is not null");
        } else {
            Assertions.assertEquals(firstName, userDTO.getFirstName(), "Incorrect first name");
        }
        if(lastName == null) {
            Assertions.assertNull(userDTO.getLastName(), "LastName is not null");
        } else {
            Assertions.assertEquals(lastName, userDTO.getLastName(), "Incorrect last name");
        }
        if(email == null) {
            Assertions.assertNull(userDTO.getEmail(), "Email is not null");
        } else {
            Assertions.assertEquals(email, userDTO.getEmail(), "Incorrect email");
        }
        Assertions.assertEquals(pwd, userDTO.getPassword(), "Incorrect Password");
        if(phone == null) {
            Assertions.assertNull(userDTO.getPhone(), "Phone is not null");
        } else {
            Assertions.assertEquals("123 45 67", userDTO.getPhone(), "Incorrect phone");
        }
        if(userStatus == null) {
            Assertions.assertNull(userDTO.getUserStatus(), "UserStatus is not null");
        } else {
            Assertions.assertEquals(userStatus, userDTO.getUserStatus(), "Incorrect userStatus");
        }
    }
    @Step("Validate user's fields for getUser")
    public static void assertGetUserRes(GetUserResponseFullDTO getUser, Long userId, String userName,
                                        String pwd, Long userStatus) {
        Assertions.assertEquals(userId, getUser.getId(), "Incorrect UserId");
        Assertions.assertEquals(userName, getUser.getUsername(), "Incorrect UserName");
        Assertions.assertEquals(pwd, getUser.getPassword(), "Incorrect Password");
        Assertions.assertEquals(userStatus, getUser.getUserStatus(), "Incorrect  UserStatus");
    }


}
