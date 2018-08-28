package com.marekhudyma.partitioning.repository.horizontal;

import com.marekhudyma.partitioning.model.horizontal.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

}
