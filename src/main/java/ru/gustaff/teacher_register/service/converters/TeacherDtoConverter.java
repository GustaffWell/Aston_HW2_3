package ru.gustaff.teacher_register.service.converters;

import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.entity.SchoolClass;
import ru.gustaff.teacher_register.entity.SchoolSubject;
import ru.gustaff.teacher_register.entity.Teacher;

import java.util.Set;
import java.util.stream.Collectors;

import static ru.gustaff.teacher_register.service.converters.SchoolClassDtoConverter.SCHOOL_CLASS_DTO_JSON_CONVERTER;
import static ru.gustaff.teacher_register.service.converters.SchoolSubjectDtoConverter.SCHOOL_SUBJECT_DTO_JSON_CONVERTER;

public class TeacherDtoConverter extends AbstractDtoJsonConverter<TeacherDto>{

    public static final TeacherDtoConverter TEACHER_DTO_JSON_CONVERTER = new TeacherDtoConverter();

    protected TeacherDtoConverter() {
        super(TeacherDto.class);
    }

    public  TeacherDto createDto(Teacher teacher) {
        Set<SchoolSubjectDto> subjects = null;
        if (teacher.getSubjects() != null) {
            subjects = teacher.getSubjects().stream()
                    .map(SCHOOL_SUBJECT_DTO_JSON_CONVERTER::createDto)
                    .collect(Collectors.toSet());
        }

        Set<SchoolClassDto> classes = null;
        if (teacher.getSupervisedClasses() != null) {
            classes = teacher.getSupervisedClasses().stream()
                    .map(SCHOOL_CLASS_DTO_JSON_CONVERTER::createDto)
                    .collect(Collectors.toSet());
        }

        return new TeacherDto(teacher.getId(), teacher.getName(),
                subjects, classes);
    }

    public Teacher createDao(TeacherDto teacherDto, int yearOfBirth) {
        Set<SchoolSubject> subjects = null;
        if (teacherDto.getSubjects() != null) {
            subjects =  teacherDto.getSubjects().stream()
                    .map(subjectDto -> SCHOOL_SUBJECT_DTO_JSON_CONVERTER.createDao(subjectDto, yearOfBirth))
                    .collect(Collectors.toSet());
        }

        Set<SchoolClass> classes = null;
        if (teacherDto.getClasses() != null) {
            classes = teacherDto.getClasses().stream()
                    .map(classDto -> SCHOOL_CLASS_DTO_JSON_CONVERTER.createDao(classDto, yearOfBirth))
                    .collect(Collectors.toSet());
        }

        return new Teacher(teacherDto.getId(), teacherDto.getName(), yearOfBirth,
                subjects, classes);
    }
}
