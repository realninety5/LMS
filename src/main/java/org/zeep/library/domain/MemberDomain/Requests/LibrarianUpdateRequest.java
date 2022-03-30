package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Setter @Getter @AllArgsConstructor @Builder
public class LibrarianUpdateRequest {
    @NotEmpty(message = "Enter the memberId")
    private String username;

    @NotEmpty(message = "You must enter a firstname")
    private String firstName;

    @NotEmpty(message = "You must enter your lastname")
    private String lastName;
}
