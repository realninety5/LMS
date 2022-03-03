package org.zeep.library.domain.BookDomain.Requests;

import lombok.*;
import org.zeep.library.Exceptions.validators.ValueOfEnum;
import org.zeep.library.enums.Genre;

import javax.validation.constraints.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookUpdateRequest {
    @NotEmpty(message = "Please enter the name of the book.")
    @Size(min = 3, max = 50)
    private String bookName;

    @NotEmpty(message = "You must enter author's details")
    private AuthorRequest author;

    @ValueOfEnum(enumClass = Genre.class)
    @NotNull(message = "Please specify a genre.")
    private Genre genre;

    @NotEmpty(message = "Please enter a valid ISBN.")
    @Size(min = 10, max = 10)
    private String isbn;

    @NotEmpty(message = "Provide a little description of the book.")
    @Size(min = 20, max = 200)
    private String desc;

    @NotEmpty(message = "please enter the publisher's name")
    private String publisher;

    @NotNull(message = "Please enter the number of pages in the book.")
    private int numOfPages;
}
