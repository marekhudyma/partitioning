package com.marekhudyma.partitioning.model.horizontal;

import lombok.RequiredArgsConstructor;

import java.util.UUID;

import static java.lang.String.format;


@RequiredArgsConstructor
public class AccountTestBuilder extends Account.AccountBuilder {

    private final int seed;

    public Account.AccountBuilder withTestDefaults() {
        return Account.builder()
                .id(new UUID(0, seed))
                .name(format("name.%d", seed))
                .additionalInfo(format("additionalInfo.%d", seed))
                .scoring(seed);
    }

}


