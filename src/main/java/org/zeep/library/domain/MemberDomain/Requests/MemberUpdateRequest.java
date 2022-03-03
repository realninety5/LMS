package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Builder
@AllArgsConstructor @NoArgsConstructor @Setter
@Getter
public class MemberUpdateRequest {

    @NotEmpty(message = "Enter your first name.")
    private String firstName;

    @NotEmpty(message = "Enter your last name.")
    private String lastName;

    @NotEmpty(message = "Enter your username.")
    private String username;
}
