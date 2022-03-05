package org.zeep.library.domain.BookDomain.Responses;


import lombok.*;
import org.zeep.library.model.BookModel;
import org.zeep.library.model.MemberModel;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {
        private Integer responseCode;
        private String message;
        private BookModel body;
}
