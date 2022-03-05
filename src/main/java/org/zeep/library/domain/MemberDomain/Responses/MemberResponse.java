package org.zeep.library.domain.MemberDomain.Responses;


import lombok.*;
import org.zeep.library.model.MemberModel;

@Builder @Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class MemberResponse {
    private Integer responseCode;
    private String message;
    private MemberModel body;
}
