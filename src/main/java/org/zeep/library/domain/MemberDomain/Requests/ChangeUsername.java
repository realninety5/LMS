package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@AllArgsConstructor @Getter @Setter @Builder
public class ChangeUsername {

    @NotEmpty(message = "Enter the username")
    private String username;

    @NotEmpty(message = "Enter your username.")
    private String newUsername;
}
