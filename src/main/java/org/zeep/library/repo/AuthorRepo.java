package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.Author;

import java.util.List;
import java.util.UUID;

public interface AuthorRepo extends JpaRepository<Author, UUID> {
    public Author findByFirstNameAndLastName(String firstName, String lastName);
    public List<Author> findByFirstName(String firstname);
}
