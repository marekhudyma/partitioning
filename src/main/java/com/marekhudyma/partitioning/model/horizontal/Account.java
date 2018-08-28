package com.marekhudyma.partitioning.model.horizontal;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.domain.Persistable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "accounts")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Account implements Persistable<UUID> {

    @Id
    private UUID id;

    @Generated(value = GenerationTime.INSERT)
    private Instant created;

    @Generated(value = GenerationTime.ALWAYS)
    private Instant updated;

    private String name;

    private String additionalInfo;

    private int scoring;

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return id == null;
    }

}
