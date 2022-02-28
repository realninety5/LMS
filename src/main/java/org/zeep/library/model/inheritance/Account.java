package org.zeep.library.model.inheritance;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.zeep.library.enums.Status;

import javax.persistence.*;
import java.util.UUID;


@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@SuperBuilder
@MappedSuperclass

public class Account {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "status")
    private Status status;

    @Column(name = "email", unique = true)
    private String email;
}