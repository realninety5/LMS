package org.zeep.library.model;



import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.zeep.library.model.inheritance.Account;

import javax.persistence.Entity;
import javax.persistence.Table;

@NoArgsConstructor
@SuperBuilder
@Entity @Table(name = "Librarian")
public class LibrarianModel extends Account {

}
