package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.UUID;


@AllArgsConstructor @NoArgsConstructor @Setter
@Getter @Builder
public class MemberUpdateRequest {

    @NotEmpty(message = "Enter the memberId")
    private String username;

    @NotEmpty(message = "Enter your first name.")
    private String firstName;

    @NotEmpty(message = "Enter your last name.")
    private String lastName;

}
