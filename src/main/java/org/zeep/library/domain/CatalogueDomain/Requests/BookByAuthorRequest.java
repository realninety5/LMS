package org.zeep.library.domain.CatalogueDomain.Requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@Setter
@Getter
@Builder

public class BookByAuthorRequest {
    @NotEmpty(message = "Please enter the author's firstName.")
    private String firstName;

    @NotEmpty(message = "Please enter the author's lastName.")
    private String lastName;
}
