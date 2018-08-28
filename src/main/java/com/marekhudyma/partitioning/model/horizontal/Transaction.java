package com.marekhudyma.partitioning.model.horizontal;

import lombok.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class Transaction implements Persistable<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sequence;

    // @Generated(value = GenerationTime.INSERT)
    private Instant created;

    @Generated(value = GenerationTime.ALWAYS)
    private Instant updated;

    private UUID accountId;

    private BigDecimal amount;

    @Override
    public Long getId() {
        return sequence;
    }

    @Override
    public boolean isNew() {
        return sequence == null;
    }

}
