package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;
import org.zeep.library.ExceptionsAndValidators.validators.ValueOfEnum;
import org.zeep.library.enums.State;

import javax.validation.constraints.*;

@AllArgsConstructor
@Setter
@Getter
public class AddressRequest {

    @ValueOfEnum(enumClass = State.class)
    @NotNull(message = "Choose one of the provided states.")
    private State state;

    @Pattern(regexp = "[09]{6}", message = "Enter a valid zipcode.")
    private int zipcode;

    @NotNull(message = "Enter a valid city")
    private String city;

    @NotEmpty(message = "Enter your street name")
    private String street;
}
