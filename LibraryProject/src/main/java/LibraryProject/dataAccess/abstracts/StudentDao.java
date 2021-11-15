package LibraryProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import LibraryProject.entities.concretes.Student;

public interface StudentDao extends JpaRepository<Student, Long> {
	Student getByStudentId (long studentId);
}
