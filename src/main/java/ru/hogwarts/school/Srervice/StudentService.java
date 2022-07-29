package ru.hogwarts.school.Srervice;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
@Service
public class StudentService {
    final private HashMap<Long, Student> studentHashMap = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student){
        student.setId(++lastId);
        studentHashMap.put(lastId,student);
        return student;
    }

    public Student editStudent(Student student) {
        if(studentHashMap.containsKey(student.getId())) {
            studentHashMap.put(student.getId(), student);
        }
        return null;
    }

    public Student deleteStudent(long id){
        return studentHashMap.remove(id);
    }

    public Student findStudent(long id) {
        return studentHashMap.get(id);
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : studentHashMap.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
