package ru.hogwarts.school.Srervice;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Exception.StudentNotFoundException;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student){
        return studentRepository.save(student);
    }

    public Student editStudent(Student student) {
        if(studentRepository.existsById(student.getId())) {
            studentRepository.save(student);
        }
        return null;
    }

    public void deleteStudent(long id){
        studentRepository.deleteById(id);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Collection<Student> findByAge(Integer age) {
        return studentRepository.findStudentByAgeIs(age);
    }

    public Collection<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAgeBetween(Integer min, Integer max) {
        return studentRepository.findByAgeBetween(min,max);
    }

    public Faculty getStudentFaculty(long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(StudentNotFoundException::new);
        if (student.getFaculty() == null) {
            throw new RuntimeException();
        }
        return student.getFaculty();
    }
}
