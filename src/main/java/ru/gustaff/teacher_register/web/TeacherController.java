package ru.gustaff.teacher_register.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gustaff.teacher_register.dto.TeacherDto;
import ru.gustaff.teacher_register.service.TeacherService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @GetMapping("/{id}")
    public TeacherDto get(@PathVariable Integer id) {
        return teacherService.get(id);
    }

    @GetMapping
    public List<TeacherDto> getAll() {
        return teacherService.getAll();
    }

    @PostMapping(value = "/{yearOfBirth}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<TeacherDto> createWithLocation(@RequestBody TeacherDto teacherDto,
                                                         @PathVariable Integer yearOfBirth) {
        TeacherDto created = teacherService.save(teacherDto, yearOfBirth);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/teacher" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/{yearOfBirth}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody TeacherDto teacherDto, @PathVariable Integer id, @PathVariable Integer yearOfBirth) {
        if (id.equals(teacherDto.getId())) {
            teacherService.save(teacherDto, yearOfBirth);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        teacherService.delete(id);
    }
}
