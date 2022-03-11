package org.zeep.library.domain.BookDomain.Requests.Book;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

//
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BookBorrowRequest {
    @NotEmpty(message = "please enter the id of the member that wants to borrow the book")
    private UUID memberId;

    @NotEmpty(message = "please enter the id of the book")
    private UUID bookId;
}
