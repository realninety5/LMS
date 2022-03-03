package org.zeep.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//@SuperBuilder
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Entity
public class BookItemModel {

    @Id
    private UUID id;

    // Starts from 1000
    @Column(name = "position")
    private String position;

    @Column(name = "borrowed_date")
    private Date borrowedDate;

    @Column(name = "available")
    private boolean available;

    @Column(name = "reserved")
    private boolean reserved;

    @Column(name = "date_added")
    private Date dateAdded;

//    @OneToOne
//    private BookModel book;
//
//    @OneToOne
//    private BookEditionModel edition;

    @OneToOne(fetch = FetchType.EAGER)//(cascade = CascadeType.ALL)
//    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel borrowedBy;
}
