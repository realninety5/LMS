package org.zeep.library.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity @Table(name = "author") @NoArgsConstructor @AllArgsConstructor
@Setter @Getter @Builder
public class Author {
    @Id
    private UUID id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "initial")
    private String initial;

    @OneToMany(targetEntity = BookItemModel.class)
    private List<BookItemModel> books;
}
