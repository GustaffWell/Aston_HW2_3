package ru.gustaff.teacher_rerister.service.converters;

import ru.gustaff.teacher_rerister.dto.SchoolClassDto;
import ru.gustaff.teacher_rerister.dto.SchoolSubjectDto;
import ru.gustaff.teacher_rerister.dto.TeacherDto;
import ru.gustaff.teacher_rerister.model.SchoolClass;
import ru.gustaff.teacher_rerister.model.SchoolSubject;
import ru.gustaff.teacher_rerister.model.Teacher;

import java.util.List;

import static ru.gustaff.teacher_rerister.service.converters.SchoolClassDtoJsonConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_rerister.service.converters.SchoolSubjectDtoJsonConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;

public class TeacherDtoJsonConverter extends AbstractDtoJsonConverter<TeacherDto> {

    public static final TeacherDtoJsonConverter TEACHER_DTO_JSON_CONVERTER = new TeacherDtoJsonConverter();

    private TeacherDtoJsonConverter() {
        super(TeacherDto.class);
    }

    public  TeacherDto createDto(Teacher teacher) {
        List<SchoolSubjectDto> subjects = teacher.getSubjects().stream()
                .map(SCHOOL_SUBJECT_DTO_JSON_CONVERTER::createDto)
                .toList();
        List<SchoolClassDto> classes = teacher.getSupervisedClasses().stream()
                .map(SCHOOL_CLASS_DTO_JSON_CONVERTER::createDto)
                .toList();
        return new TeacherDto(teacher.getId(), teacher.getName(),
                subjects, classes);
    }

    public Teacher createDao(TeacherDto teacherDto, int yearOfBirth) {
        List<SchoolSubject> subjects = teacherDto.getSubjects().stream()
                .map(subjectDto -> SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDao(subjectDto, yearOfBirth))
                .toList();
        List<SchoolClass> classes = teacherDto.getClasses().stream()
                .map(classDto -> SCHOOL_CLASS_DTO_JSON_CONVERTER.createDao(classDto, yearOfBirth))
                .toList();
        return new Teacher(teacherDto.getId(), teacherDto.getName(), yearOfBirth,
                subjects, classes);
    }
}
