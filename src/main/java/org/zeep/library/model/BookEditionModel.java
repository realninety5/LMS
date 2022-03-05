package org.zeep.library.model;


import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


//@SuperBuilder
@Entity @Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BookEditionModel {

    @Id
    private UUID id;

    @Column(name = "edition")
    private String edition;

    // @param: value to encode(isbn), format(qrcode), width, height
    @Column(name = "barcode") @Lob
    private byte[] barcode;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "published")
    private Date published;

    @Column(name = "num_of_pages")
    private int numOfPages;

    @OneToMany(targetEntity = BookItemModel.class)
    private List<BookItemModel> allBooks;

    public List<BookItemModel> getAllBooks() {
        List<BookItemModel> avail = new ArrayList<>();
        for (BookItemModel book: this.allBooks){
            if (book.isAvailable()){
                avail.add(book);
            }
        }
        return avail;
    }
}
