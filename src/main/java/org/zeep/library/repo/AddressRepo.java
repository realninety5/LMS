package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.Address;

public interface AddressRepo extends JpaRepository<Address, Long> {
}
