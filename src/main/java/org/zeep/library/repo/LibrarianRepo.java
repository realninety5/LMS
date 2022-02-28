package org.zeep.library.repo;

import org.zeep.library.model.LibrarianModel;
import org.zeep.library.repo.AccountRepo;

public interface LibrarianRepo extends AccountRepo {
    public LibrarianModel findByUsername(String username);
}
