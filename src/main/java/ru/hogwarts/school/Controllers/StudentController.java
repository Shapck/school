package ru.hogwarts.school.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.Srervice.StudentService;

import java.util.Collection;
import java.util.Collections;

@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
        return ResponseEntity.ok(studentService.createStudent(student));
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Student studentFound = studentService.findStudent(id);
        if (studentFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentService.findStudent(id));
    }

    @PutMapping
    public ResponseEntity<Student> editStudent(@RequestBody Student student) {
        Student studentFound = studentService.editStudent(student);
        if (studentFound == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studentFound);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity findStudents(@RequestParam(required = false) Integer age,
                                       @RequestParam(required = false) Integer min,
                                       @RequestParam(required = false) Integer max) {
        if (min != null || max != null) {
            return ResponseEntity.ok(studentService.findByAgeBetween(min, max));
        }
        if (age != null) {
            return ResponseEntity.ok(studentService.findByAge(age));
        }
        return ResponseEntity.ok(studentService.getAllStudents());
    }
}

