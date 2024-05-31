package ru.gustaff.teacher_register.model;

import java.util.List;
import java.util.Objects;

public class Teacher extends AbstractBaseEntity{
    private int yearOfBirth;
    private List<SchoolSubject> subjects;
    private List<SchoolClass> supervisedClasses;

    public Teacher(Integer id, String name, int age, List<SchoolSubject> subjects, List<SchoolClass> supervisedClasses) {
        super(id, name);
        this.yearOfBirth = age;
        this.subjects = subjects;
        this.supervisedClasses = supervisedClasses;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<SchoolSubject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<SchoolSubject> subjects) {
        this.subjects = subjects;
    }

    public List<SchoolClass> getSupervisedClasses() {
        return supervisedClasses;
    }

    public void setSupervisedClasses(List<SchoolClass> supervisedClasses) {
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
