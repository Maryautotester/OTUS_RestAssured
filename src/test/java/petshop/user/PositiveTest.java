package petshop.user;

import static org.hamcrest.Matchers.equalTo;

import assertation.Assert;
import io.restassured.response.ValidatableResponse;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Проверить, что юзер со всеми параметрами успешно создается")
    public void createUserAllArgs() {
        ResSpecification resspec = new ResSpecification();
        UserDTO userdto = UserDTO.builder()
                .id(12345L)
                .username("BestUser")
                .firstName("Ivan")
                .lastName("Egorov")
                .email("IEgorov@w.ru")
                .phone("123 45 67")
                .password("Ivan12345")
                .userStatus(123L)
                .build();
        resspec.createUser(userdto)
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo("12345"));

        RegSpecification reqspec = new RegSpecification();
        reqspec.regSpecificationGet("BestUser");
        ValidatableResponse response = new ResSpecification().getUser();
        UserDTO userDTO = response.extract().body().as(UserDTO.class);

        response.statusCode(200);

        Assert.assertUserRes(userDTO, 12345L, "BestUser", "Ivan", "Egorov",
                "IEgorov@w.ru", "123 45 67", "Ivan12345", 123L);
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
    @DisplayName("Проверить, что юзер с параметрами по умолчанию создается")
    public void createUserDefaultArgs() {
        ResSpecification resspec = new ResSpecification();
        UserDTO userDTO = UserDTO.builder()
                .id(12346L)
                .username("DefaultUser")
                .password("Def123")
                .build();

        Assert.assertUserRes(userDTO, 12346L, "DefaultUser", null, null,
                null, null, "Def123", null);

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
        Assert.assertGetUserRes(getUser, 12346L,"DefaultUser", "Def123", 0L);

    }
}
