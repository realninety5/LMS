package org.zeep.library.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.*;

@JsonIgnoreProperties("availBooks")
@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder @Table(name = "edition")
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BookEditionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "edition")
    private String edition;

    @Column(name = "book_id")
    private UUID bookId;

    // @param: value to encode(isbn), format(qrcode), width, height
    @Column(name = "barcode") @Lob
    private byte[] barcode;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "year_published")
    private String published;

    @Column(name = "num_of_pages")
    private int numOfPages;

    @JsonIgnoreProperties("books")
    @ManyToOne
    @JoinColumn(name = "year_id")//, nullable = false)
    private BookByYear year;

    @OneToMany(targetEntity = BookItemModel.class)
    private Set<BookItemModel> allBooks = new HashSet<>();

    public Set<BookItemModel> getAvailBooks() {
        Set<BookItemModel> avail = new HashSet<>();
        for (BookItemModel book: this.allBooks){
            if (book.isAvailable()){
                avail.add(book);
            }
        }
        return avail;
    }
}
