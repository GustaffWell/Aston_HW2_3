package ru.gustaff.teacher_register.dto;

import java.util.Objects;
import java.util.Set;

public class TeacherDto {
    private Integer id;
    private String name;
    private Set<SchoolSubjectDto> subjects;
    private Set<SchoolClassDto> classes;

    public TeacherDto(Integer id, String name, Set<SchoolSubjectDto> subjects, Set<SchoolClassDto> classes) {
        this.id = id;
        this.name = name;
        this.subjects = subjects;
        this.classes = classes;
    }

    public TeacherDto() {
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

    public Set<SchoolSubjectDto> getSubjects() {
        return subjects;
    }

    public void setSubjects(Set<SchoolSubjectDto> subjects) {
        this.subjects = subjects;
    }

    public Set<SchoolClassDto> getClasses() {
        return classes;
    }

    public void setClasses(Set<SchoolClassDto> classes) {
        this.classes = classes;
    }

    @Override
    public String toString() {
        return "TeacherDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", subjects=" + subjects +
                ", classes=" + classes +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherDto that = (TeacherDto) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(subjects, that.subjects) && Objects.equals(classes, that.classes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, subjects, classes);
    }
}
