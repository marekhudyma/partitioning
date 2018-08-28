package com.marekhudyma.partitioning.repository.horizontal;

import com.marekhudyma.partitioning.model.horizontal.Account;
import com.marekhudyma.partitioning.model.horizontal.AccountTestBuilder;
import com.marekhudyma.partitioning.model.horizontal.TransactionTestBuilder;
import com.marekhudyma.partitioning.model.horizontal.Transaction;
import com.marekhudyma.partitioning.util.AbstractIntegrationTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.concurrent.ThreadLocalRandom;

import static org.assertj.core.api.Assertions.assertThat;

class TransactionRepositoryIntegrationTest extends AbstractIntegrationTest {

    @Autowired
    private TransactionRepository underTest;

    @Autowired
    private AccountRepository accountRepository;

    private Account account;

    @BeforeEach
    void setUp() {
         account = createAccount();
    }

    @Test
    void shouldCreateTransaction() throws Exception {
        Transaction transaction = new TransactionTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults()
                .accountId(account.getId())
                .created(LocalDate.of(2015, 5, 1).atStartOfDay().toInstant(ZoneOffset.UTC))
                .build();


        underTest.save(transaction);

        Transaction actual = underTest.findById(transaction.getId()).get();
        assertThat(actual).isEqualToIgnoringNullFields(transaction);

    }

    @Test
    void shouldSaveForEachMonth() {
        LocalDate from = LocalDate.of(2015, 1, 1);
        LocalDate to = LocalDate.of(2019, 12, 31);

        while (from.isBefore(to)) {
            Transaction transaction = new TransactionTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults()
                    .accountId(account.getId())
                    .created(from.atStartOfDay().toInstant(ZoneOffset.UTC))
                    .build();
            underTest.save(transaction);

            Transaction actual = underTest.findById(transaction.getId()).get();
            assertThat(actual).isEqualToIgnoringNullFields(transaction);
            from = from.plusMonths(1);
        }
    }

    /**
     * If this test fail, create a new partition !
     */
    @Test
    void shouldInsertRowForNowPlusInterval() {
        int intervalInMonths = 3;
        underTest.save(new TransactionTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults()
                .accountId(account.getId())
                .created(LocalDate.now().plusMonths(intervalInMonths).atStartOfDay().toInstant(ZoneOffset.UTC))
                .build());
    }

    private Account createAccount() {
        Account account = new AccountTestBuilder(ThreadLocalRandom.current().nextInt()).withTestDefaults().build();
        return accountRepository.save(account);
    }


}
