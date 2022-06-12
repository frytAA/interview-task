package com.example.interviewtask.common.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

/**
 * This interface describes common basic functionality required by all JPA Entity implementations.
 */
public interface JpaBaseEntity extends Serializable {
    /**
     * Returns the primary key identifier
     *
     * @return PK Identifier
     */
    Long getId();

    /**
     * Returns the optimistic lock version managed by the JPA persistence provider.
     *
     * @return Optimistic Lock Version
     */
    int getVersion();

    /**
     * Returns the entity creation timestamp in UTC timezone.
     *
     * @return creation timestamp (UTC)
     * @see LocalDateTime
     */
    LocalDateTime getCreationDate();

    /**
     * Returns the entity update timestamp in UTC timezone.
     *
     * @return creation timestamp (UTC)
     * @see LocalDateTime
     */
    Optional<LocalDateTime> getUpdateDate();

    /**
     * Returns whether the entity instance is in an unpersisted state or not.
     *
     * @return Entity is persisted or not
     */
    default boolean isNew() {
        return getId() == null || getId() == 0;
    }
}
