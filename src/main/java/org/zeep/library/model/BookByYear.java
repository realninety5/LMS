package org.zeep.library.model;

import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "year") @NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class BookByYear {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "year")
    private String year;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "year")
    private Set<BookEditionModel> books;
}
