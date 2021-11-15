package LibraryProject.business.concretes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LibraryProject.business.abstracts.TakingBookService;
import LibraryProject.dataAccess.abstracts.BookDao;
import LibraryProject.dataAccess.abstracts.StudentDao;
import LibraryProject.dataAccess.abstracts.TakingBookDao;
import LibraryProject.entities.concretes.Book;
import LibraryProject.entities.concretes.Student;
import LibraryProject.entities.concretes.TakingBook;

@Service
public class TakingBookManager implements TakingBookService {

	private TakingBookDao takingBookDao;

	@Autowired
	private BookDao bookDao;

	@Autowired
	private StudentDao studentDao;

	@Autowired
	public TakingBookManager(TakingBookDao takingBookDao) {
		super();
		this.takingBookDao = takingBookDao;
	}

	@Override
	public void take(TakingBook takingBook) {

		List<TakingBook> takingBookDataList = takingBookDao.findAll();

		for (int i = 0; i <= takingBookDataList.size(); i++) {
			if (takingBookDataList.size() != 0 && takingBookDataList.get(i).getBookId() == takingBook.getBookId()
					&& takingBookDataList.get(i).getStudentId() == takingBook.getStudentId()
					&& takingBookDataList.get(i).getReturnDate() == null) {
				System.out.println("bu öğrenci için bu kitabı alma kaydı zaten daha önceden var");
				return;
			} else if (i == takingBookDataList.size() - 2 || takingBookDataList.size() == 0) {
				Student student = studentDao.findById(takingBook.getStudentId()).orElse(null);
				Book book = bookDao.findById(takingBook.getBookId()).orElse(null);
				if (student == null) {
					System.out.println("böyle bir öğrenci yok");
					return;
				}
				if (student.getLateQuantity() > 0) {
					System.out.println("Bu öğrenci cezalı");
					return;
				}
				if (book == null) {
					System.out.println("böyle bir kitap yok");
					return;
				}
				if (student.getNumberOfBooks() >= 3) {
					System.out.println("Bir öğrenci maksimum 3 adet kitap alabilir");
					return;
				}
				if (book.getStockQuantity() <= 0) {
					System.out.println("Bu kitap stoklarda yok");
					return;
				}

				Date now = new Date();
				SimpleDateFormat simpleformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				takingBook.setTakeDate(simpleformatter.format(now));
				System.out.println("Kitap alma işlemi çalıştı");
				this.takingBookDao.save(takingBook);

				book.setStockQuantity(book.getStockQuantity() - 1);
				this.bookDao.save(book);

				student.setNumberOfBooks(student.getNumberOfBooks() + 1);

				this.studentDao.save(student);

				return;

			}
		}

	}

	@Override
	public void give(TakingBook takingBook) {

		List<TakingBook> takingBookDataList = takingBookDao.findAll();

		SimpleDateFormat simpleformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		Student student = studentDao.findById(takingBook.getStudentId()).orElse(null);
		Book book = bookDao.findById(takingBook.getBookId()).orElse(null);

		for (int i = 0; i <= takingBookDataList.size(); i++) {
			try {
				if (takingBookDataList.size() != 0 && takingBookDataList.get(i).getBookId() == takingBook.getBookId()
						&& takingBookDataList.get(i).getStudentId() == takingBook.getStudentId()
						&& takingBookDataList.get(i).getReturnDate() == null) {

					Date now = new Date();

					takingBookDataList.get(i).setReturnDate(simpleformatter.format(now));

					long oneDayLongValue = 1000 * 60 * 60 * 24;
					long a = now.getTime();
					long b = simpleformatter.parse(takingBookDataList.get(i).getTakeDate()).getTime();
					if ((a - b) / oneDayLongValue >= 14) {
						student.setLateQuantity(student.getLateQuantity() + 1);
						System.out.println("Gecikmiş");
					}

					System.out.println("Kitap teslim etme işlemi çalıştı");

					book.setStockQuantity(book.getStockQuantity() + 1);
					this.bookDao.save(book);

					student.setNumberOfBooks(student.getNumberOfBooks() - 1);
					this.studentDao.save(student);

					return;
				}

				else if (takingBookDataList.size() == 0 || i == takingBookDataList.size() - 1) {
					System.out.println("Bu öğrenci idsi ve kitap idsi ile kayıtlı işlem yok veya kitap teslim edilmiş");
					return;
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}

		}

	}

	@Override
	public List<TakingBook> lateGetAll() {
		List<TakingBook> takingBookDataList = takingBookDao.findAll();
		List<TakingBook> lateList = new ArrayList<TakingBook>();

		SimpleDateFormat simpleformatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		Date now = new Date();
		long oneDayLongValue = 1000 * 60 * 60 * 24;
		long a = now.getTime();

		for (int i = 0; i < takingBookDataList.size(); i++) {
			try {
				long b = simpleformatter.parse(takingBookDataList.get(i).getTakeDate()).getTime();

				TakingBook takingBook = takingBookDao.findById(takingBookDataList.get(i).getTakingBookId())
						.orElse(null);
				if (takingBook.getReturnDate() == null && (a - b) / oneDayLongValue >= 14) {
					takingBook.setBook((bookDao.findById(takingBookDataList.get(i).getBookId()).orElse(null)));
					takingBook.setStudent((studentDao.findById(takingBookDataList.get(i).getStudentId()).orElse(null)));
					lateList.add(takingBook);
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}

		return lateList;
	}

	@Override
	public List<TakingBook> getAll() {
		List<TakingBook> takingBookDataList = takingBookDao.findAll();
		for (int i = 0; i < takingBookDataList.size(); i++) {
			takingBookDataList.get(i).setBook((bookDao.findById(takingBookDataList.get(i).getBookId()).orElse(null)));
			takingBookDataList.get(i)
					.setStudent((studentDao.findById(takingBookDataList.get(i).getStudentId()).orElse(null)));
		}
		return takingBookDataList;
	}

	@Override
	public TakingBook getByTakingBookId(long id) {
		List<TakingBook> takingBookDataList = takingBookDao.findAll();
		for (int i = 0; i < takingBookDataList.size(); i++) {
			takingBookDataList.get(i).setBook((bookDao.findById(takingBookDataList.get(i).getBookId()).orElse(null)));
			takingBookDataList.get(i)
					.setStudent((studentDao.findById(takingBookDataList.get(i).getStudentId()).orElse(null)));
		}
		return this.takingBookDao.getByTakingBookId(id);
	}

}
