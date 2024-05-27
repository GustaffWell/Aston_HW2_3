package ru.gustaff.teacher_rerister.model;

import java.util.Objects;

public class SchoolSubject extends AbstractBaseEntity {
    private int hoursPerWeek;

    public SchoolSubject(Integer id, String name, int hoursPerWeek) {
        super(id, name);
        this.hoursPerWeek = hoursPerWeek;
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
