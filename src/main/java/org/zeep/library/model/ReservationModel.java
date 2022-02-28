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

    @OneToMany(targetEntity = BookModel.class, fetch = FetchType.EAGER)
    private List<BookModel> reserves;

    @Column(name = "date")
    private Date date;
}
