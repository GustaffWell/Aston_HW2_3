package ru.gustaff.teacher_rerister.dto;

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

    public void setId(int id) {
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
}
