package org.zeep.library.domain.MemberDomain.Requests;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Builder @Getter @AllArgsConstructor
public class GetMemberRequest {
    @NotEmpty(message = "Enter a Username")
    @Size(min = 4, max = 20, message = "Last name must be between 10 and 20 characters")
    private String username;
}
