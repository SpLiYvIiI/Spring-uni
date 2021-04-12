package ge.tsu.lecture4.exercise.student.service;

import ge.tsu.lecture4.exercise.student.util.RecordAlreadyExistsException;
import ge.tsu.lecture4.exercise.student.util.RecordNotFoundException;
import ge.tsu.lecture4.exercise.student.modules.NewStudentView;
import ge.tsu.lecture4.exercise.student.modules.StudentView;

import java.io.IOException;
import java.util.List;

public interface StudentService {
   public void add(NewStudentView newStudentView) throws RecordAlreadyExistsException, IOException;
   public List<StudentView> getStudents() throws IOException;
   public StudentView getStudent(String id) throws RecordNotFoundException, IOException;
   public void deleteStudent(String id) throws RecordNotFoundException, IOException;
}
