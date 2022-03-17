package org.zeep.library.domain.MemberDomain.Requests;

import lombok.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class UpdateAddress {
    @NotEmpty(message = "Enter the memberId")
    private UUID memberId;

    @NotNull(message = "Enter your address.")
    private AddressRequest address;
}
