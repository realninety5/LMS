package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.zeep.library.ExceptionsAndValidators.validators.ValueOfEnum;
import org.zeep.library.enums.AccountType;
import org.zeep.library.enums.Gender;

import javax.validation.constraints.*;
import java.time.*;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@SuperBuilder
@Getter
public class CreateMember extends LibrarianCreateRequest{

    @NotNull(message = "Enter a valid date of birth")
    private LocalDate dob;

    @NotNull(message = "Choose one of the provided genders")
    @ValueOfEnum(enumClass = Gender.class)
    private Gender gender;

    @NotNull(message = "Choose one of the provided account types.")
    @ValueOfEnum(enumClass = AccountType.class)
    private AccountType accountType;

    @NotNull(message = "Enter your address.")
    private AddressRequest address;

    public int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }
}
