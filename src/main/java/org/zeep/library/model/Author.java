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

    @ManyToMany(targetEntity = BookItemModel.class)
    private List<BookModel> books;

    public BookModel getBook(UUID ids) {
        for (BookModel book: books) {
            if(book.getId().compareTo(ids)==0) return book;
        }
        return null;
    }
}
