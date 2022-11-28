package ru.hogwarts.school.Srervice;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.FacultyNotFoundException;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.*;

@Service
public class FacultyService {
    final private FacultyRepository facultyRepository;

    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }


    public Faculty createFaculty(Faculty faculty){
        return facultyRepository.save(faculty);
    }

    public Faculty editFaculty(Faculty faculty) {
        if (facultyRepository.existsById(faculty.getId())) {
            return facultyRepository.save(faculty);
        }
        return null;
    }

    public void deleteFaculty(long id){
         facultyRepository.deleteById(id);
    }

    public Faculty findFaculty (long id) {
        return facultyRepository.findById(id).get();
    }

    public Collection<Faculty> findByColorOrName(String color, String name){
        return facultyRepository.findFacultyByColorOrNameContainingIgnoreCase(color, name);
    }
    public Collection<Faculty> findAll() {
        return facultyRepository.findAll();
    }

    public List<Student> getFacultyStudents(long id) {
        return facultyRepository.findById(id)
                .orElseThrow(FacultyNotFoundException::new)
                .getStudents();
    }
}
