package LibraryProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LibraryProject.business.abstracts.StudentService;
import LibraryProject.entities.concretes.Student;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/students/")
@CrossOrigin
public class StudentsController {
	private StudentService studentService;

	@Autowired
	public StudentsController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@PostMapping("add")
	public void add(@RequestBody Student student) {
		this.studentService.add(student);
	}

	@GetMapping("getAll")
	public List<Student> getAll() {
		return this.studentService.getAll();
	}

	@GetMapping("getByStudentId")
	public Student getByStudentId(long studentId) {
		return this.studentService.getByStudentId(studentId);
	}

}
