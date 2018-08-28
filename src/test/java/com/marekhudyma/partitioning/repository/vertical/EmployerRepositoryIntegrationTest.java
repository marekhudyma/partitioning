package com.marekhudyma.partitioning.repository.vertical;

import com.marekhudyma.partitioning.model.vertical.Employer;
import com.marekhudyma.partitioning.model.vertical.EmployerDetail;
import com.marekhudyma.partitioning.model.vertical.EmployerDetailTestBuilder;
import com.marekhudyma.partitioning.model.vertical.EmployerTestBuilder;
import com.marekhudyma.partitioning.repository.vertical.EmployerDetailRepository;
import com.marekhudyma.partitioning.repository.vertical.EmployerRepository;
import com.marekhudyma.partitioning.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class EmployerRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private EmployerRepository employerRepository;

    @Autowired
    private EmployerDetailRepository employerDetailRepository;

    @Test
    void shouldCreateAccount() throws Exception {
        Employer employer = new EmployerTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        employer.setEmployerDetail(null);
        employerRepository.save(employer);

        Employer actual = employerRepository.findById(employer.getId()).get();
        assertThat(actual).isEqualToIgnoringNullFields(employer);
    }

    @Test
    void shouldCreateAccountWithDetails() throws Exception {
        EmployerDetail employerDetail = new EmployerDetailTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        employerDetailRepository.save(employerDetail);

        Employer employer = new EmployerTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        employer.setEmployerDetail(employerDetail);
        employerRepository.save(employer);

        Employer actual = employerRepository.findById(employer.getId()).get();
        assertThat(actual).isEqualToIgnoringNullFields(employer);
    }

}