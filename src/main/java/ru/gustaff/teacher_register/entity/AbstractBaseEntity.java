package ru.gustaff.teacher_register.entity;

import jakarta.persistence.*;

@MappedSuperclass
public abstract class AbstractBaseEntity {

    public static final int START_SEQ = 100000;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    @SequenceGenerator(name = "global_seq", sequenceName = "school.global_seq", allocationSize = 1, initialValue = START_SEQ)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    protected AbstractBaseEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    protected AbstractBaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isNew() {
        return id == null;
    }
}
