package ru.gustaff.teacher_register.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name = "school_subject", schema = "school")
public class SchoolSubject extends AbstractBaseEntity {

    @Column(name = "hours_per_week", nullable = false)
    private int hoursPerWeek;

    public SchoolSubject(Integer id, String name, int hoursPerWeek) {
        super(id, name);
        this.hoursPerWeek = hoursPerWeek;
    }

    public SchoolSubject() {
    }

    public int getHoursPerWeek() {
        return hoursPerWeek;
    }

    public void setHoursPerWeek(int hoursPerWeek) {
        this.hoursPerWeek = hoursPerWeek;
    }

    @Override
    public String toString() {
        return "SchoolSubject{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", hoursPerWeek=" + hoursPerWeek +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolSubject that = (SchoolSubject) o;
        return Objects.equals(this.getId(), that.getId()) && hoursPerWeek == that.hoursPerWeek && Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), hoursPerWeek);
    }
}
