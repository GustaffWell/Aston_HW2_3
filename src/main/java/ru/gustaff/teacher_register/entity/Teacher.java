package ru.gustaff.teacher_register.entity;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teacher", schema = "school")
public class Teacher extends AbstractBaseEntity{

    @Column(name = "year_of_birth", nullable = false)
    private int yearOfBirth;

    @ManyToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(name = "teacher_subject",
        joinColumns = @JoinColumn(name = "teacher_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "subject_id", referencedColumnName = "id"))
    private Set<SchoolSubject> subjects;

    @OneToMany(fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
    @JoinColumn(name = "teacher_id")
    private Set<SchoolClass> supervisedClasses;


    public Teacher(Integer id, String name, int yearOfBirth, Set<SchoolSubject> subjects, Set<SchoolClass> supervisedClasses) {
        super(id, name);
        this.yearOfBirth = yearOfBirth;
        this.subjects = subjects;
        this.supervisedClasses = supervisedClasses;
    }

    public Teacher() {
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public Set<SchoolSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SchoolSubject> subjects) {
        this.subjects = subjects;
    }

    public Set<SchoolClass> getSupervisedClasses() {
        return supervisedClasses;
    }

    public void setSupervisedClasses(Set<SchoolClass> supervisedClasses) {
        this.supervisedClasses = supervisedClasses;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + this.getId() +
                ", name='" + this.getName() + '\'' +
                ", yearOfBirth=" + yearOfBirth + ",\n" +
                "subjects=" + subjects + ",\n" +
                "supervisedClasses=" + supervisedClasses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(this.getId(), teacher.getId()) && yearOfBirth == teacher.yearOfBirth && Objects.equals(this.getName(), teacher.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), yearOfBirth);
    }
}
