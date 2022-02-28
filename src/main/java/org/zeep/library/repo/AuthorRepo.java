package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.Author;

import java.util.UUID;

public interface AuthorRepo extends JpaRepository<Author, UUID> {
    public Author findByAuthorName(String authorName);
}
