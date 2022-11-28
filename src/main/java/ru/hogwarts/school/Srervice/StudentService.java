package ru.hogwarts.school.Srervice;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
@Service
public class StudentService {

    StudentRepository studentRepository;

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
        return studentRepository.findById(id).get();
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

}
