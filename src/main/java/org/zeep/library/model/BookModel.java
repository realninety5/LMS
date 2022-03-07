package org.zeep.library.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.zeep.library.enums.Genre;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
//@SuperBuilder
@Entity @Table(name = "book")//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "book_name")
    private String bookName;

    @JsonIgnoreProperties("books")
    @ManyToMany(mappedBy = "books", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)//(targetEntity = Author.class)
    private Set<Author> author = new HashSet<>();

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
