package org.zeep.library.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.zeep.library.model.inheritance.*;
import org.zeep.library.enums.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "Member")
public class MemberModel extends Account {

    @Column(name = "date_of_birth")
    private LocalDate dob;

    @Column(name = "gender")
    private Gender gender;

    @Column(name = "account_type")
    private AccountType accountType;

//    @Column(name = "books_borrowed")
//    private int booksBorrowedCount;

    @Column(name = "date_reg")
    private Date date_reg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library_card_id", referencedColumnName = "id")
    private LibraryCardModel libraryCard;

    @OneToMany(mappedBy = "borrowedBy", fetch = FetchType.EAGER) // must not have more than 3 books
    private Set<BookItemModel> borrowedBooks;

    @OneToMany(mappedBy = "reservedBy", fetch = FetchType.EAGER) // must not have more than 3 books
    private Set<BookItemModel> reservedBooks;
}
