package com.marekhudyma.partitioning.model.vertical;

import com.marekhudyma.partitioning.model.vertical.Employer;
import lombok.RequiredArgsConstructor;

import static java.lang.String.format;


@RequiredArgsConstructor
public class EmployerTestBuilder extends Employer.EmployerBuilder {

    private final int seed;

    public Employer.EmployerBuilder withTestDefaults() {
        return Employer.builder()
                .firstname(format("firstname.%d", seed))
                .lastname(format("firstname.%d", seed));
    }

}


