package org.zeep.library.domain.AuthorDomain.Response;


import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.zeep.library.model.Author;

import javax.validation.constraints.NotEmpty;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Builder
public class AuthorResponse {
    private String message;
    private Author body;
    private Integer responseCode;
}
