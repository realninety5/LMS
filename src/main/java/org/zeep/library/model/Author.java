package org.zeep.library.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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

    @JsonIgnoreProperties("author")
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)//(targetEntity = BookItemModel.class)
    @JoinTable(
            name = "author_books",
            joinColumns = {@JoinColumn(name = "author_id")},
            inverseJoinColumns = {@JoinColumn(name = "movie_id")}
    )
    private Set<BookModel> books = new HashSet<>();

    public BookModel getBook(UUID ids) {
        for (BookModel book: books) {
            if(book.getId().compareTo(ids)==0) return book;
        }
        return null;
    }
}
