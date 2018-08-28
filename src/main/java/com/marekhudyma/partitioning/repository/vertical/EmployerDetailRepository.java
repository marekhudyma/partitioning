package com.marekhudyma.partitioning.repository.vertical;

import com.marekhudyma.partitioning.model.vertical.EmployerDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployerDetailRepository extends JpaRepository<EmployerDetail, Long> {

}
