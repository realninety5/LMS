package org.zeep.library.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

//@SuperBuilder
@Builder @AllArgsConstructor @NoArgsConstructor @Getter @Setter @Entity @Table(name = "book_item")
public class BookItemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "edition_id")
    private UUID editionId;

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

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MemberModel borrowedBy;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)//(cascade = CascadeType.ALL)
    private MemberModel reservedBy;
}
