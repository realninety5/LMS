package org.zeep.library.domain.MemberDomain.Requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter @Builder
public class UpdateAddress {

    @NotEmpty(message = "Enter the username")
    private String username;

    @NotNull(message = "Enter your address.")
    private AddressRequest address;
}
