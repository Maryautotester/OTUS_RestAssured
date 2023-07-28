package petshop;

import static org.hamcrest.Matchers.equalTo;

import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PetStoreTest {

    /*
    Test check that user with all arguments successfully created.
    StatusCode is 200, type is unknown, message is 12345
     */
    @Order(1)
    @Test
    public void createUserAllArgs() {

        CreateUserApi createUserApi = new CreateUserApi();
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
        createUserApi.createUser(userDTO)
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("12345"));
    }

    /*
    Test check that user with default set of arguments(id, username, password) successfully created.
    All undefined fields is null in request.
    StatusCode is 200, type is unknown, message is 12346
     */
    @Order(2)
    @Test
    public void createUserDefaultArgs() {
        CreateUserApi createUserApi = new CreateUserApi();
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

        createUserApi.createUser(userDTO)
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("12346"));
    }
    /*
    Test check that DefaultUser from test 2 was created in DB.
    Status code is 200.
    id, UserName, password, UserStatus are correct.
     */
    @Order(3)
    @Test
    public void getUserByName_DefaultUser() {
        GetUserApi getUserApi = new GetUserApi("DefaultUser");
        ValidatableResponse response = getUserApi.getUser();
        GetUserResponseFull getUser = response.extract().body().as(GetUserResponseFull.class);

        response.statusCode(200);
        Assertions.assertEquals(12346L, getUser.getId(), "Incorrect UserId");
        Assertions.assertEquals("DefaultUser", getUser.getUsername(), "Incorrect UserName");
        Assertions.assertEquals("Def123", getUser.getPassword(), "Incorrect Password");
        Assertions.assertEquals(0L, getUser.getUserStatus(), "Incorrect  UserStatus");


    }
    /*
    Test check error "User not found" for user101.
    Status code is 404, Status Line is "HTTP/1.1 404 Not Found",
    code 1, type is error, message is User not found.
     */
    @Order(4)
    @Test
    public void getUserByName_User1() {
        GetUserApi getUserApi = new GetUserApi("user101");
        ValidatableResponse response = getUserApi.getUser();
        GetUserResponseDTO getUser = response.extract().body().as(GetUserResponseDTO.class);

        response.statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        Assertions.assertEquals(1, getUser.getCode(), "code is not 1");
        Assertions.assertEquals("error", getUser.getType(), "type is not error");
        Assertions.assertEquals("User not found", getUser.getMessage(), "message is not error");
    }
    /*
    Test check this endpoint couldn't be used with POST method.
     */
    @Order(5)
    @Test
    public void getUserByName_InvalidUserName() {
        GetUserApi getUserApi = new GetUserApi("123");
        getUserApi.getUserInvalid().statusCode(405).statusLine("HTTP/1.1 405 Method Not Allowed");
    }
}

