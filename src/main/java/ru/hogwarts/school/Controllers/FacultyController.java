package ru.hogwarts.school.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Srervice.FacultyService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public ResponseEntity createFaculty(@RequestBody Faculty faculty) {
        return ResponseEntity.ok(facultyService.createFaculty(faculty));
    }

    @GetMapping("{id}")
    public ResponseEntity getFaculty(@PathVariable Long id) {
        Faculty facultyFound = facultyService.findFaculty(id);
        if (facultyFound == null) {
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.findFaculty(id));
    }

    @PutMapping
    public ResponseEntity editFaculty(@RequestBody Faculty faculty) {
        Faculty facultyFound = (Faculty) facultyService.editFaculty(faculty);
        if (facultyFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(facultyService.editFaculty(faculty));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findFacultiesByColor(@RequestParam String color,
                                               @RequestParam String name) {
        if (color != null && !color.isBlank() || name != null && !name.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColorOrName(color,name));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }


}
