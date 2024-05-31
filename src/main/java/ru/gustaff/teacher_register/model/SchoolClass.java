package ru.gustaff.teacher_register.model;

import java.util.Objects;

public class SchoolClass extends AbstractBaseEntity {
    private int studentsCount;

    public SchoolClass(Integer id, String name, int studentsCount) {
        super(id, name);
        this.studentsCount = studentsCount;
    }

    public int getStudentsCount() {
        return studentsCount;
    }

    public void setStudentsCount(int studentsCount) {
        this.studentsCount = studentsCount;
    }

    @Override
    public String toString() {
        return "SchoolClass{" +
                "id=" + this.getId() +
                ", numberAndLetter='" + this.getName() + '\'' +
                ", studentsCount=" + studentsCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchoolClass that = (SchoolClass) o;
        return Objects.equals(this.getId(), that.getId()) && studentsCount == that.studentsCount && Objects.equals(this.getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId(), this.getName(), studentsCount);
    }
}
