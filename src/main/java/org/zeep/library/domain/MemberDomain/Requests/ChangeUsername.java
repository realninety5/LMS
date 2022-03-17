package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@AllArgsConstructor @Getter @Setter
public class ChangeUsername {

    @NotEmpty(message = "Enter the memberId")
    private UUID memberId;

    @NotEmpty(message = "Enter your username.")
    private String username;
}
