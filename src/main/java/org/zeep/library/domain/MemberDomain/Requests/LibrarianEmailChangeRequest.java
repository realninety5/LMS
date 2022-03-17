package org.zeep.library.domain.MemberDomain.Requests;

import lombok.*;

import javax.validation.constraints.*;
import java.util.UUID;

@Setter @Getter @AllArgsConstructor
public class LibrarianEmailChangeRequest {
    @NotEmpty(message = "Enter the memberId")
    private UUID librarianId;

    @NotEmpty(message = "Enter a valid email.")
    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
            message = "Please enter a valid email.")
    private String email;
}
