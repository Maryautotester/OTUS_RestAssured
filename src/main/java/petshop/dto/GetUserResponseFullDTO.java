package petshop.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GetUserResponseFullDTO {
    private Long id;
    private String password;
    private String username;
    private Long userStatus;
    private String firstname;
    private String lastname;

}
