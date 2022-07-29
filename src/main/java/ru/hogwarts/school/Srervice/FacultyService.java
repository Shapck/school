package ru.hogwarts.school.Srervice;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.Model.Faculty;
import ru.hogwarts.school.Model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Objects;

@Service
public class FacultyService {

    final private HashMap<Long, Faculty> facultyHashMap = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty){
        faculty.setId(++lastId);
        facultyHashMap.put(lastId,faculty);
        return faculty;
    }

    public Faculty editFaculty(Faculty faculty) {
        if (facultyHashMap.containsKey(faculty.getId())){
        facultyHashMap.put(faculty.getId(),faculty);
        }
        return null;
    }

    public Faculty deleteFaculty(long id){
        return facultyHashMap.remove(id);
    }

    public Faculty findFaculty (long id) {
        return facultyHashMap.get(id);
    }

    public Collection<Faculty> findByColor(String color){
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultyHashMap.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }

}
