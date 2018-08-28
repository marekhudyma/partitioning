package com.marekhudyma.partitioning.repository.horizontal;


import com.marekhudyma.partitioning.model.horizontal.Account;
import com.marekhudyma.partitioning.model.horizontal.AccountTestBuilder;
import com.marekhudyma.partitioning.util.AbstractIntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


class AccountRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void shouldCreateAccount() throws Exception {
        Account account = new AccountTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        accountRepository.save(account);

        Account actual = accountRepository.findById(account.getId()).get();
        assertThat(actual).isEqualToIgnoringNullFields(account);
    }

    @Test
    void shouldNotCreateTwoAccountWithTheSameName() throws Exception {
        accountRepository.save(new AccountTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults()
                .name("notUniqueName")
                .build());

        assertThrows(DataIntegrityViolationException.class,
                () -> accountRepository.save(new AccountTestBuilder(ThreadLocalRandom.current().nextInt())
                        .withTestDefaults()
                        .name("notUniqueName")
                        .build()));
    }

    @Test
    void shouldFindByName() throws Exception {
        Account account = new AccountTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        accountRepository.save(account);

        Optional<Account> actual = accountRepository.findByName(account.getName());
        assertThat(actual.get()).isEqualToIgnoringNullFields(account);
    }

    @Test
    void shouldNotFindByName() throws Exception {
        Optional<Account> actual = accountRepository.findByName(UUID.randomUUID().toString());
        assertThat(actual).isEmpty();
    }

}

