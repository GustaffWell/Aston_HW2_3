package ru.gustaff.teacher_register.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gustaff.teacher_register.dto.SchoolSubjectDto;
import ru.gustaff.teacher_register.service.SchoolSubjectService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/subject")
public class SchoolSubjectController {

    private final SchoolSubjectService schoolSubjectService;

    @Autowired
    public SchoolSubjectController(SchoolSubjectService schoolSubjectService) {
        this.schoolSubjectService = schoolSubjectService;
    }

    @GetMapping("/{id}")
    public SchoolSubjectDto get(@PathVariable Integer id) {
        return schoolSubjectService.get(id);
    }

    @GetMapping
    public List<SchoolSubjectDto> getAll() {
        return schoolSubjectService.getAll();
    }

    @PostMapping(value = "/{hoursPerWeek}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SchoolSubjectDto> createWithLocation(@RequestBody SchoolSubjectDto schoolSubjectDto,
                                                         @PathVariable Integer hoursPerWeek) {
        SchoolSubjectDto created = schoolSubjectService.save(schoolSubjectDto, hoursPerWeek);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/subject" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/{hoursPerWeek}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody SchoolSubjectDto schoolSubjectDto, @PathVariable Integer id,
                       @PathVariable Integer hoursPerWeek) {
        if (id.equals(schoolSubjectDto.getId())) {
            schoolSubjectService.save(schoolSubjectDto, hoursPerWeek);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        schoolSubjectService.delete(id);
    }
}
