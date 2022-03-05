package org.zeep.library.domain.BookDomain.Requests;


import lombok.*;
import org.zeep.library.Exceptions.validators.ValueOfEnum;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookEditionModel;

import javax.persistence.Column;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookAddRequest {

    @NotEmpty(message = "Please enter the name of the book.")
    @Size(min = 3, max = 50)
    private String bookName;

    @NotEmpty(message = "You must enter author's details")
    private AuthorRequest author;

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

    @NotNull(message = "Please enter the date the bok was published.")
    private Date published;

    @NotNull(message = "Please enter the number of pages in the book.")
    private int numOfPages;

    @NotEmpty(message = "Enter book's edition.")
    private String edition;
}
