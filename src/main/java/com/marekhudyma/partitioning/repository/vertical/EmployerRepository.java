package com.marekhudyma.partitioning.repository.vertical;

import com.marekhudyma.partitioning.model.vertical.Employer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerRepository extends JpaRepository<Employer, Long> {

}
