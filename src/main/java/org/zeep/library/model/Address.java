package org.zeep.library.model;

import lombok.*;
import org.zeep.library.enums.State;
import org.zeep.library.model.MemberModel;

import javax.persistence.*;

@Table(name = "Address")
@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column(name = "state")
    private State state;
    @Column(name = "zipcode")
    private int zipcode;
    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

//    @OneToOne(mappedBy = "address")
//    private MemberModel memberModel;
}
