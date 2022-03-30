package org.zeep.library.domain.BookDomain.Requests.Manage;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Getter @AllArgsConstructor
public class BookRemoveRequest {
    @NotEmpty(message = "Please enter the bookId")
    private UUID bookId;
}
