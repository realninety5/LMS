package org.zeep.library.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;



@Entity
@Table(name = "borrowed_books")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BooksBorrowed {

        @Id @GeneratedValue(strategy = GenerationType.AUTO)
        private UUID id;

        @OneToOne
        @JoinColumn(name = "books_borrowed_id", referencedColumnName = "id")
        private BookItemModel bookItem;

        // one member cannot borrow more than 3 books at anytime
        @OneToOne
        @JoinColumn(name = "member_id", referencedColumnName = "id")
        private MemberModel borrowedBy;

        // The date it was borrowed
        @Column(name = "date")
        private Date date;

    }
