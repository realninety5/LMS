package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class PasswordChangeRequest {

    @NotEmpty(message = "Please enter your old password.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
            message = "Password must contain one uppercase, one lowercase, one digit, and one special symbol," +
                    ".*?")
    private String oldPassword;

    @NotEmpty(message = "Please enter your new password.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
            message = "Password must contain one uppercase, one lowercase, one digit, and one special symbol," +
                    ".*?")
    private String newPassword;

    @NotEmpty(message = "Please enter your new password again.")
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
            message = "Password must contain one uppercase, one lowercase, one digit, and one special symbol," +
                    ".*?")
    private String newPassword2;
}
