package user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserInformation {
    private String firstName;
    private String lastName;
    private String postalCode;
}
