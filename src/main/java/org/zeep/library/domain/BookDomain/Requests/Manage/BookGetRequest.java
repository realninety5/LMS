package org.zeep.library.domain.BookDomain.Requests.Manage;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class BookGetRequest {
    @NotEmpty(message = "Enter the book's id, please")
    private UUID bookId;
}
