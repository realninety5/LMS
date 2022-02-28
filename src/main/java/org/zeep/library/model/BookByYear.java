package org.zeep.library.model;

import lombok.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(targetEntity = BookItemModel.class)
    private List<BookItemModel> books;
}
