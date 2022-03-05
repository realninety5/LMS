package org.zeep.library.config;


import lombok.AllArgsConstructor;
import lombok.Data;

import org.zeep.library.enums.AccountType;
import org.zeep.library.model.LibraryCardModel;

import java.util.Date;

@Data @AllArgsConstructor
public class LibraryCardGenerator {
    public LibraryCardModel create(AccountType accountType) {
        LibraryCardModel card = LibraryCardModel.builder()
                .issuedDate(new Date()).accountType(accountType)
                .build();
        card.setExpDate(card.getExpDate());
        return card;
    }
}
