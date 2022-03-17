package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Setter @Getter @AllArgsConstructor
public class LibrarianUpdateRequest {
    @NotEmpty(message = "Enter the memberId")
    private UUID librarianId;

    @NotEmpty(message = "You must enter a firstname")
    private String firstName;

    @NotEmpty(message = "You must enter your lastname")
    private String lastName;
}
