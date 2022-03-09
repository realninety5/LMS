package org.zeep.library.domain.BookDomain.Requests.Manage;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.UUID;

@Data
@NoArgsConstructor
public class BookGetRequest {
    @NotEmpty(message = "Enter the book's id, please")
    private UUID id;
}
