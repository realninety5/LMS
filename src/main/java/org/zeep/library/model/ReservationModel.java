package org.zeep.library.model;


import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "reservation")
@NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class ReservationModel {

    @Id
    private UUID id;

    // The reserved book
    @Column(name = "reserved_book")
    private BookModel reservedBook;

    // Who reserved the book
    @Column(name = "reserved_by")
    private MemberModel reservedBy;

    // The date it was reserved
    @Column(name = "date")
    private Date date;
}
