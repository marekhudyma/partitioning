package com.marekhudyma.partitioning.repository.horizontal;

import com.marekhudyma.partitioning.model.horizontal.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, UUID> {

    Optional<Account> findByName(String name);
}
