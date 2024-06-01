package ru.gustaff.teacher_register.dto;

import java.util.Objects;

public class SchoolClassDto {

    private Integer id;
    private String numberAndLetter;

    public SchoolClassDto() {
    }

    public SchoolClassDto(Integer id, String numberAndLetter) {
        this.id = id;
        this.numberAndLetter = numberAndLetter;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumberAndLetter() {
        return numberAndLetter;
    }

    public void setNumberAndLetter(String numberAndLetter) {
        this.numberAndLetter = numberAndLetter;
    }

    @Override
    public String toString() {
        return "SchoolClassDto{" +
                "id=" + id +
                ", numberAndLetter='" + numberAndLetter + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClassDto that = (SchoolClassDto) o;
        return Objects.equals(id, that.id) && Objects.equals(numberAndLetter, that.numberAndLetter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, numberAndLetter);
    }
}
