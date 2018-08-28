package com.marekhudyma.partitioning.repository.vertical;

import com.marekhudyma.partitioning.model.vertical.EmployerDetail;
import com.marekhudyma.partitioning.model.vertical.EmployerDetailTestBuilder;
import com.marekhudyma.partitioning.repository.vertical.EmployerDetailRepository;
import com.marekhudyma.partitioning.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class EmployerDetailsRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EmployerDetailRepository employerDetailRepository;

    @Test
    void shouldCreateAccountDetail() throws Exception {
        EmployerDetail employerDetail = new EmployerDetailTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        employerDetailRepository.save(employerDetail);

        EmployerDetail actual = employerDetailRepository.findById(employerDetail.getId()).get();
        assertThat(actual).isEqualToIgnoringNullFields(employerDetail);
    }

}