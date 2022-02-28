package org.zeep.library.model;


import lombok.*;
import org.zeep.library.enums.Genre;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
//@SuperBuilder
@Entity //@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "book_name")
    private String bookName;

    @OneToOne(targetEntity = Author.class)
    private Author author;

    @Column(name = "genre")
    private Genre genre;

    @OneToMany(targetEntity = BookEditionModel.class)
    private List<BookEditionModel> editions;
}
