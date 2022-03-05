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

    @ManyToMany(targetEntity = Author.class)
    private List<Author> author;

    @Column(name = "genre")
    private Genre genre;

    @Column(name = "desc")
    private String desc;

    @OneToMany(targetEntity = BookEditionModel.class)
    private List<BookEditionModel> editions;

    public BookEditionModel getEdition(String edition) {
        for (BookEditionModel editionF: editions) {
            if(editionF.getEdition().equalsIgnoreCase(edition)) {
                return editionF;
            }
        }
        return null;
    }
}
