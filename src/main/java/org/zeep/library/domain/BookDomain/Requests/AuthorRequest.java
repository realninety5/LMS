package org.zeep.library.domain.BookDomain.Requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;


@Data @AllArgsConstructor @NoArgsConstructor @Builder
public class AuthorRequest {

    @NotEmpty(message = "Please enter the author's firstname")
    private String firstName;

    @NotEmpty(message = "Please enter the author's firstname")
    private String lastName;

    private String initial;
}
