package org.zeep.library.model;

import lombok.*;
import org.zeep.library.enums.Genre;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "genre")
    private Set<BookModel> books;
}
