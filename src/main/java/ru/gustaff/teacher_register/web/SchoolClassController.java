package ru.gustaff.teacher_register.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.gustaff.teacher_register.dto.SchoolClassDto;
import ru.gustaff.teacher_register.service.SchoolClassService;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/class")
public class SchoolClassController {

    private final SchoolClassService schoolClassService;

    @Autowired
    public SchoolClassController(SchoolClassService schoolClassService) {
        this.schoolClassService = schoolClassService;
    }

    @GetMapping("/{id}")
    public SchoolClassDto get(@PathVariable Integer id) {
        return schoolClassService.get(id);
    }

    @GetMapping
    public List<SchoolClassDto> getAll() {
        return schoolClassService.getAll();
    }

    @PostMapping(value = "/{studentsCount}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SchoolClassDto> createWithLocation(@RequestBody SchoolClassDto schoolClassDto,
                                                               @PathVariable Integer studentsCount) {
        SchoolClassDto created = schoolClassService.save(schoolClassDto, studentsCount);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/api/class" + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}/{studentsCount}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody SchoolClassDto schoolClassDto, @PathVariable Integer id,
                       @PathVariable Integer studentsCount) {
        if (id.equals(schoolClassDto.getId())) {
            schoolClassService.save(schoolClassDto, studentsCount);
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        schoolClassService.delete(id);
    }
}
