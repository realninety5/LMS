package org.zeep.library.model;


import lombok.*;
import lombok.experimental.SuperBuilder;
import org.zeep.library.model.inheritance.*;
import org.zeep.library.enums.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

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

    @Column(name = "books_borrowed")
    private int booksBorrowedCount;

    @Column(name = "date_reg")
    private Date date_reg;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "library_card_id", referencedColumnName = "id")
    private LibraryCardModel libraryCard;

    @OneToMany(targetEntity = BookItemModel.class)
    private List<BookItemModel> booksBorrowed;

    @OneToMany(targetEntity = ReservationModel.class)
    private List<ReservationModel> reserves;
}
