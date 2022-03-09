package org.zeep.library.domain.MemberDomain.Requests;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.zeep.library.Exceptions.validators.ValueOfEnum;
import org.zeep.library.enums.AccountType;
import org.zeep.library.enums.Gender;

import javax.validation.constraints.*;
import java.time.*;


@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateMember extends LibrarianCreateRequest{

//    @NotEmpty(message = "Enter a Username")
//    @Size(min = 4, max = 20, message = "Last name must be between 10 and 20 characters")
//    private String username;
//
//    @NotEmpty(message = "Enter a login password")
//    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[^\\w\\s]).{8,}$",
//            message = "Password must contain one uppercase, one lowercase, one digit, and one special symbol," +
//                    ".*?")
//    private String password;
//
//    @NotEmpty(message = "You must enter a firstname")
//    private String firstName;
//
//    @NotEmpty(message = "You must enter your lastname")
//    private String lastName;
//
//    @NotEmpty(message = "Enter a valid email.")
//    @Pattern(regexp = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$",
//            message = "Please enter a valid email.")
//    private String email;

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
