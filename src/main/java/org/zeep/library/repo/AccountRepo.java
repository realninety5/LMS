package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.inheritance.Account;

import java.util.UUID;

public interface AccountRepo extends JpaRepository<Account, UUID>  {
}
