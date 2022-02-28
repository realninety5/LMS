package org.zeep.library.repo;

import org.zeep.library.model.MemberModel;
import org.zeep.library.repo.AccountRepo;

public interface MemberRepository extends AccountRepo {
    public MemberModel findByUsername(String username);
}
