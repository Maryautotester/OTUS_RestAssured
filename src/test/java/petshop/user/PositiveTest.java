package petshop.user;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import petshop.dto.GetUserResponseFullDTO;
import petshop.dto.UserDTO;
import petshop.spec.RegSpecification;
import petshop.spec.ResSpecification;

public class PositiveTest {

    /*
    Test check that user with all arguments successfully created.
    StatusCode is 200, type is unknown, message is 12345

    Check that BestUser was created in DB.
    Status code is 200.
    All fields are correct.
     */
    @Test
    public void createUserAllArgs() {
        ResSpecification resspec = new ResSpecification();
        UserDTO userDTO = UserDTO.builder()
                .id(12345L)
                .username("BestUser")
                .firstName("Ivan")
                .lastName("Egorov")
                .email("IEgorov@w.ru")
                .phone("123 45 67")
                .password("Ivan12345")
                .userStatus(123L)
                .build();
        resspec.createUser(userDTO)
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("12345"));

        RegSpecification reqspec = new RegSpecification();
        reqspec.regSpecificationGet("BestUser");
        ValidatableResponse response = new ResSpecification().getUser();
        UserDTO userdto = response.extract().body().as(UserDTO.class);

        response.statusCode(200);
        Assertions.assertEquals(12345L, userdto.getId(), "Incorrect UserId");
        Assertions.assertEquals("BestUser", userdto.getUsername(), "Incorrect UserName");
        Assertions.assertEquals("Ivan", userdto.getFirstName(), "Incorrect first name");
        Assertions.assertEquals("Egorov", userdto.getLastName(), "Incorrect last name");
        Assertions.assertEquals("IEgorov@w.ru", userdto.getEmail(), "Incorrect email");
        Assertions.assertEquals("123 45 67", userdto.getPhone(), "Incorrect phone");
        Assertions.assertEquals("Ivan12345", userdto.getPassword(), "Incorrect Password");
        Assertions.assertEquals(123L, userdto.getUserStatus(), "Incorrect  UserStatus");
    }

    /*
    Test check that user with default set of arguments(id, username, password) successfully created.
    All undefined fields is null in request.
    StatusCode is 200, type is unknown, message is 12346

    Check that DefaultUser was created in DB.
    Status code is 200.
    id, UserName, password, UserStatus are correct.
     */
    @Test
    public void createUserDefaultArgs() {
        ResSpecification resspec = new ResSpecification();
        UserDTO userDTO = UserDTO.builder()
                .id(12346L)
                .username("DefaultUser")
                .password("Def123")
                .build();
        Assertions.assertEquals(12346, userDTO.getId(), "Incorrect ID");
        Assertions.assertEquals("DefaultUser", userDTO.getUsername(), "Incorrect Username");
        Assertions.assertNull(userDTO.getFirstName(), "FirstName is not null");
        Assertions.assertNull(userDTO.getLastName(), "LastName is not null");
        Assertions.assertNull(userDTO.getEmail(), "Email is not null");
        Assertions.assertEquals("Def123", userDTO.getPassword(), "Incorrect Password");
        Assertions.assertNull(userDTO.getPhone(), "Phone is not null");
        Assertions.assertNull(userDTO.getUserStatus(), "UserStatus is not null");

        resspec.createUser(userDTO)
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("12346"));

        RegSpecification reqspec = new RegSpecification();
        reqspec.regSpecificationGet("DefaultUser");
        ValidatableResponse response = new ResSpecification().getUser();
        GetUserResponseFullDTO getUser = response.extract().body().as(GetUserResponseFullDTO.class);

        response.statusCode(200);
        Assertions.assertEquals(12346L, getUser.getId(), "Incorrect UserId");
        Assertions.assertEquals("DefaultUser", getUser.getUsername(), "Incorrect UserName");
        Assertions.assertEquals("Def123", getUser.getPassword(), "Incorrect Password");
        Assertions.assertEquals(0L, getUser.getUserStatus(), "Incorrect  UserStatus");
    }
}
