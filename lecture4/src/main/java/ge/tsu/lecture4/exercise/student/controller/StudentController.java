package ge.tsu.lecture4.exercise.student.controller;

import ge.tsu.lecture4.exercise.student.util.RecordAlreadyExistsException;
import ge.tsu.lecture4.exercise.student.util.RecordNotFoundException;
import ge.tsu.lecture4.exercise.student.serviceImpl.StudentServiceImpl;
import ge.tsu.lecture4.exercise.student.modules.NewStudentView;
import ge.tsu.lecture4.exercise.student.modules.StudentView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentServiceImpl studentService;


    @PostMapping("/students")
    public void addStudent(@RequestBody NewStudentView newStudentView) throws RecordAlreadyExistsException, IOException {
        studentService.add(newStudentView);
    }
    @GetMapping("/students")
    public List<StudentView> getStudents() throws IOException {
        return studentService.getStudents();
    }
    @GetMapping("/students/{id}")
    public StudentView getStudent(@PathVariable String id) throws RecordNotFoundException, IOException{
        return studentService.getStudent(id);
    }
    @DeleteMapping("students/{id}")
    public void deleteStudent(@PathVariable String id) throws RecordNotFoundException, IOException{
        studentService.deleteStudent(id);
    }

}
