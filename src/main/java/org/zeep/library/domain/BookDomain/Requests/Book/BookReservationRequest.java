package org.zeep.library.domain.BookDomain.Requests.Book;


import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Setter @Builder
@Getter
public class BookReservationRequest {
    @NotEmpty(message = "please enter the id of the member that wants to borrow the book")
    private String username;

    @NotEmpty(message = "please enter the id of the book")
    private UUID bookId;
}
