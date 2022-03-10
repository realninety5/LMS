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

    @OneToOne
    @JoinColumn(name = "books_reserved_id", referencedColumnName = "id")
    private BookModel book;

    @ManyToOne
    @JoinColumn(name = "member_id", referencedColumnName = "id")
    private MemberModel reservedBy;

    // The date it was reserved
    @Column(name = "date")
    private Date date;
}
