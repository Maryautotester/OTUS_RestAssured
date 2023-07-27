package PetShop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class GetUserResponseFull {
    private Long id;
    private String password;
    private String username;
    private Long userStatus;
}
