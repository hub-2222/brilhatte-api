package com.brilhatte.app.common;

import jakarta.persistence.*;

import java.util.Objects;

@MappedSuperclass
public class AbstractEntity implements BaseEntity {

    public static final String SEQUENCE_GENERATOR = "seq";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQUENCE_GENERATOR)
    @Column(name = "id", nullable = false)
    private Long id;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractEntity that = (AbstractEntity) o;
        return Objects.equals(id, that.id);
    }
}