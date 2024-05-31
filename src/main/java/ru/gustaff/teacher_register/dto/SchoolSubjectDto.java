package ru.gustaff.teacher_register.dto;

public class SchoolSubjectDto {

    private Integer id;
    private String name;

    public SchoolSubjectDto() {
    }

    public SchoolSubjectDto(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "SchoolSubjectDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
