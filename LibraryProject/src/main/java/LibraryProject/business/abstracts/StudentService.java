package LibraryProject.business.abstracts;

import java.util.List;

import LibraryProject.entities.concretes.Student;

public interface StudentService {
	void add(Student student);

	List<Student> getAll();

	Student getByStudentId(long studentId);
	
}
