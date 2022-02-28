package org.zeep.library.model;

import lombok.*;
import org.zeep.library.enums.Genre;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "genre") @NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class GenreModel {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "genre")
    private Genre genre;

    @OneToMany(targetEntity = BookItemModel.class)
    private List<BookItemModel> books;
}
