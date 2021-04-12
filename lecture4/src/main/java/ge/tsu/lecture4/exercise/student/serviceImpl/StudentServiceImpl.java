package ge.tsu.lecture4.exercise.student.serviceImpl;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ge.tsu.lecture4.exercise.student.service.StudentService;
import ge.tsu.lecture4.exercise.student.util.RecordAlreadyExistsException;
import ge.tsu.lecture4.exercise.student.util.RecordNotFoundException;
import ge.tsu.lecture4.exercise.student.modules.NewStudentView;
import ge.tsu.lecture4.exercise.student.modules.StudentView;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class StudentServiceImpl implements StudentService {
    private final static String path = "src/main/java/ge/tsu/lecture4/exercise/student/db/students.json";
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void add(NewStudentView newStudentView) throws RecordAlreadyExistsException, IOException {
        System.out.println(newStudentView.toString());
        List<StudentView> studentList = objectMapper.readValue(
                new File(path),
                new TypeReference<List<StudentView>>(){});
        for(StudentView student : studentList){
            if(student.getFirstName().equals(newStudentView.getFirstName()) && student.getLastName().equals(newStudentView.getLastName())){
                throw new RecordAlreadyExistsException(
                        String.format("Student with %s and %s already exists", newStudentView.getFirstName(), newStudentView.getLastName()));
            }
        }
        StudentView toAdd = new StudentView(
                UUID.randomUUID().toString(),
                newStudentView.getFirstName(),
                newStudentView.getLastName(),
                newStudentView.getAge(),
                newStudentView.getGPA()
        );
        studentList.add(toAdd);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path),studentList);
    }
    @Override
    public List<StudentView> getStudents() throws IOException {
        List<StudentView> studentList = objectMapper.readValue(new File(path), new TypeReference<List<StudentView>>(){});
        return studentList;
    }

    @Override
    public StudentView getStudent(String id) throws RecordNotFoundException, IOException {
        List<StudentView> studentList = objectMapper.readValue(new File(path), new TypeReference<List<StudentView>>(){});
        for(StudentView student : studentList){
            if(student.getId().equals(id)) return student;
        }
        throw new RecordNotFoundException("User with " + id + " not found");
    }
    @Override
    public void deleteStudent(String id) throws RecordNotFoundException, IOException {
        List<StudentView> studentList = objectMapper.readValue(new File(path), new TypeReference<List<StudentView>>(){});
        for(Iterator<StudentView> it = studentList.iterator(); it.hasNext();)
            if (it.next().getId().equals(id)) {
                it.remove();
                objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path),studentList);
                return;
            }
        throw new RecordNotFoundException("Unable to find student with specified id");
    }
}
