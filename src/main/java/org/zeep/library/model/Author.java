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

    @Column(name = "name")
    private String authorName;

    @OneToMany(targetEntity = BookItemModel.class)
    private List<BookItemModel> books;
}
