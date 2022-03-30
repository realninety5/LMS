package org.zeep.library.DTO;

import lombok.*;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.*;

import java.util.Set;
import java.util.UUID;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor @Builder
public class BookDTO {
    private Set<Author> author;
    private String bookName;
    private GenreModel genre;
    private String desc;
    private String edition;
    private byte[] barcode;
    private String publisher;
    private String published;
    private int numOfPages;
    private UUID bookId;
    private String position;
    private boolean available;
    private boolean reserved;

    public BookDTO mapBookToYear(BookItemModel itemModel, BookEditionModel editionModel, BookModel bookModel) {

        return BookDTO.builder().bookId(itemModel.getId()).position(itemModel.getPosition())
                .author(bookModel.getAuthor()).desc(bookModel.getDesc()).genre(bookModel.getGenre())
                .numOfPages(editionModel.getNumOfPages()).published(editionModel.getPublished())
                .publisher(editionModel.getPublisher()).bookName(bookModel.getBookName())
                .reserved(itemModel.isReserved()).available(itemModel.isAvailable()).build();
    }
}
