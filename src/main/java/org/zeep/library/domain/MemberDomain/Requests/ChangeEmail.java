package org.zeep.library.domain.MemberDomain.Requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter @Builder
public class ChangeEmail {
    @NotEmpty(message = "Enter a valid email.")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Please enter a valid email.")
    private String email;

    @NotEmpty(message = "Enter the username")
    private String username;


}
