package com.marekhudyma.partitioning.model.vertical;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "employer_details")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class EmployerDetail implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Generated(value = GenerationTime.INSERT)
    private Instant created;

    @Generated(value = GenerationTime.ALWAYS)
    private Instant updated;

    private String bankAccount;

    private String streetNumber;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

}


