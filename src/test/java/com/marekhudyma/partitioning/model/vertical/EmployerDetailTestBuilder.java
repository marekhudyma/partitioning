package com.marekhudyma.partitioning.model.vertical;

import com.marekhudyma.partitioning.model.vertical.EmployerDetail;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;

@RequiredArgsConstructor
public class EmployerDetailTestBuilder extends EmployerDetail.EmployerDetailBuilder {

    private final int seed;

    public EmployerDetail.EmployerDetailBuilder withTestDefaults() {
        return EmployerDetail.builder()
                .bankAccount(format("bankAccount.%d", seed))
                .streetNumber(format("bankAccount.%d", seed));
    }

}