package org.zeep.library.domain.BookDomain.Requests.Manage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.UUID;


@Getter @Setter @AllArgsConstructor @Builder
public class EditionAddRequest {

    @NotEmpty(message = "Enter the book's id.")
    private UUID bookId;

    @NotEmpty(message = "Please enter a valid ISBN.")
    @Size(min = 10, max = 13)
    private String isbn;

    @NotEmpty(message = "please enter the publisher's name")
    private String publisher;

    @NotNull(message = "Please enter the date the bok was published.")
    private String published;

    @NotNull(message = "Please enter the number of pages in the book.")
    private int numOfPages;

    @NotEmpty(message = "Enter book's edition.")
    private String edition;
}
