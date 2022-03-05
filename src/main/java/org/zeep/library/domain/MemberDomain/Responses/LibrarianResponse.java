package org.zeep.library.domain.MemberDomain.Responses;

import lombok.*;
import org.zeep.library.model.LibrarianModel;
import org.zeep.library.model.MemberModel;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LibrarianResponse {
    private Integer responseCode;
    private String message;
    private LibrarianModel body;
}
