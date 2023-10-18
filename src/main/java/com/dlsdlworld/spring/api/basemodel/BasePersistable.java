package com.dlsdlworld.spring.api.basemodel;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 */
@Immutable
@DynamicInsert
@DynamicUpdate
@MappedSuperclass
@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class BasePersistable implements Serializable {

    /** ID*/
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasePersistable that = (BasePersistable) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
//        return super.hashCode();
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "BaseEntity[" +
                    "id=" + id +
                "]";
    }
}