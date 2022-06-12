package com.example.interviewtask.common.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

@MappedSuperclass
public abstract class BaseEntity implements JpaBaseEntity {
    /**
     * Sequence Generator name associated to the default {@link Id @Id} property
     */
    public static final String SEQ_GENERATOR_NAME = "SEQ_GENERATOR";

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_GENERATOR_NAME)
    @Column(name = "[ID]", unique = true, nullable = false, insertable = false, updatable = false)
    protected Long id;

    @Version
    @Column(name = "[VERSION_OPT_LOCK]", nullable = false)
    protected int version;

    @NotNull
    @Column(name = "[CREATION_DATE_UTC]", nullable = false, updatable = false)
    private LocalDateTime creationDate = LocalDateTime.now(ZoneOffset.UTC);

    @LastModifiedDate
    @Column(name = "[UPDATE_DATE_UTC]")
    private LocalDateTime updateDate = null;


    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public int getVersion() {
        return this.version;
    }

    @Override
    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public Optional<LocalDateTime> getUpdateDate() {
        return Optional.ofNullable(updateDate);
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    @Override
    public String toString() {
        return getDefaultToStringBuilder().build();
    }

    ToStringBuilder getDefaultToStringBuilder() {
        return new ToStringBuilder(this)
                .append("id", getId())
                .append("version", getVersion());
    }
}
