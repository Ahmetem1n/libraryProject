package LibraryProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LibraryProject.business.abstracts.StudentService;
import LibraryProject.dataAccess.abstracts.StudentDao;
import LibraryProject.entities.concretes.Student;

@Service
public class StudentManager implements StudentService {
	private StudentDao studentDao;

	@Autowired
	public StudentManager(StudentDao studentDao) {
		super();
		this.studentDao = studentDao;
	}

	@Override
	public void add(Student student) {

		List<Student> studentDataList = this.studentDao.findAll();
		for (int i = 0; i <= studentDataList.size(); i++) {
			if (studentDataList.size() != 0
					&& studentDataList.get(i).getNationalityId().equals(student.getNationalityId())) {
				System.out.println("Bu kimlik numarasıyla daha önce kayıt yapılmış.");
				return;
			} else if (i == studentDataList.size() - 1 || studentDataList.size() == 0) {
				if (student.getGender().equalsIgnoreCase("erkek")) {
					student.setGenderUrl(
							"https://w7.pngwing.com/pngs/116/247/png-transparent-public-toilet-male-bathroom-black-man-furniture-text-logo-thumbnail.png");
				} else if (student.getGender().equalsIgnoreCase("kadın")) {
					student.setGenderUrl(
							"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSFklLHqB5eX8vSdbGOdFPrtwfbkVeN5mnQQA&usqp=CAU");
				} else {
					student.setGenderUrl(
							"https://w7.pngwing.com/pngs/903/953/png-transparent-question-mark-red-question-mark-capitalization-number-question-check-mark-thumbnail.png");
				}
				System.out.println("Öğrenci kaydetme işlemi yapıldı.");
				student.setNumberOfBooks(0);
				student.setLateQuantity(0);
				this.studentDao.save(student);
			}
		}

	}

	@Override
	public List<Student> getAll() {
		return this.studentDao.findAll();

	}

	@Override
	public Student getByStudentId(long studentId) {
		return this.studentDao.getByStudentId(studentId);
	}

}
