package org.zeep.library.config;


import lombok.*;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.zeep.library.enums.AccountType;
import org.zeep.library.model.LibraryCardModel;

import java.util.Date;

@Data @AllArgsConstructor
//@Configuration
public class LibraryCardGenerator {
//    @Bean
    public LibraryCardModel create(AccountType accountType, String firstName, String lastName) {
        LibraryCardModel card = LibraryCardModel.builder()
                .issuedDate(new Date()).accountType(accountType)
                .firstName(firstName)
                .lastName(lastName)
                .build();
        card.setExpDate(card.getExpDate());
        return card;
    }
}
