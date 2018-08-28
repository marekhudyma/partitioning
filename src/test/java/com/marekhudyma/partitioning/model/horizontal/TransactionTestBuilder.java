package com.marekhudyma.partitioning.model.horizontal;

import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@RequiredArgsConstructor
public class TransactionTestBuilder extends Transaction.TransactionBuilder {

    private final int seed;

    public Transaction.TransactionBuilder withTestDefaults() {
        return Transaction.builder()
                .accountId(new UUID(0, seed))
                .amount(BigDecimal.valueOf(seed, 2));
    }

}


