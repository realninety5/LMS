package org.zeep.library.domain.BookDomain.Requests.Book;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

//
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class BookBorrowRequest {
    @NotEmpty(message = "please enter the id of the member that wants to borrow the book")
    private String username;

    @NotEmpty(message = "please enter the id of the book")
    private UUID bookId;
}
