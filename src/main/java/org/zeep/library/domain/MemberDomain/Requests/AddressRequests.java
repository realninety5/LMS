package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;
import org.zeep.library.Exceptions.validators.ValueOfEnum;
import org.zeep.library.enums.State;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class AddressRequests {

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
