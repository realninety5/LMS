package org.zeep.library.domain.BookDomain.Requests.Manage;


import lombok.*;
import org.zeep.library.ExceptionsAndValidators.validators.ValueOfEnum;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.enums.Genre;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class BookAddRequest {

    @NotEmpty(message = "Please enter the name of the book.")
    @Size(min = 3, max = 50)
    private String bookName;

    @NotEmpty(message = "You must enter author's details")
    private Set<AuthorGetRequest> author;

    @ValueOfEnum(enumClass = Genre.class)
    @NotNull(message = "Please specify a genre.")
    private Genre genre;

    @NotEmpty(message = "Please enter a valid ISBN.")
    @Size(min = 10, max = 13)
    private String isbn;

    @NotEmpty(message = "Provide a little description of the book.")
    @Size(min = 20, max = 400)
    private String desc;

    @NotEmpty(message = "please enter the publisher's name")
    private String publisher;

    @NotNull(message = "Please enter the year the book was published.")
    private String published;

    @NotNull(message = "Please enter the number of pages in the book.")
    private int numOfPages;

    @NotEmpty(message = "Enter book's edition.")
    private String edition;
}
