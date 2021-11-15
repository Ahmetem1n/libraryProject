package LibraryProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LibraryProject.business.abstracts.BookService;
import LibraryProject.dataAccess.abstracts.BookCategoryDao;
import LibraryProject.dataAccess.abstracts.BookDao;
import LibraryProject.entities.concretes.Book;
import LibraryProject.entities.concretes.BookCategory;

@Service
public class BookManager implements BookService {
	private BookDao bookDao;
	@Autowired
	private BookCategoryDao bookCategoryDao;

	@Autowired
	public BookManager(BookDao bookDao) {
		super();
		this.bookDao = bookDao;
	}

	@Override
	public void add(Book book) {
		List<BookCategory> categoryDataList = bookCategoryDao.findAll();
		List<Book> bookDataList = bookDao.findAll();

		for (int i = 0; i <= categoryDataList.size(); i++) {
			if (categoryDataList.size() != 0 && categoryDataList.get(i).getCategoryId() == (book.getCategoryId())) {
				for (int j = 0; j <= bookDataList.size(); j++) {
					if (bookDataList.size() != 0 && bookDataList.get(j).getBarcode().equals(book.getBarcode())) {
						System.out.println("Bu barkod daha önceden girilmiş.");
						return;
					} else if (j == bookDataList.size() - 1 || bookDataList.size() == 0) {
						System.out.println("Kitap ekleme işlemi gerçekleşti");
						this.bookDao.save(book);
						return;
					}

				}
			} else if (i == categoryDataList.size() - 1 || categoryDataList.size() == 0) {
				System.out.println("Böyle bir kategori kaydı yok.");
				return;
			}
		}

	}

	@Override
	public List<Book> getAll() {
		List<Book> bookDataList = bookDao.findAll();
		for (int i = 0; i < bookDataList.size(); i++) {
			bookDataList.get(i)
					.setBookCategory((bookCategoryDao.findById(bookDataList.get(i).getCategoryId()).orElse(null)));

		}
		return bookDataList;
	}

	@Override
	public Book getByBookId(long bookId) {
		List<Book> bookDataList = bookDao.findAll();
		for (int i = 0; i < bookDataList.size(); i++) {
			bookDataList.get(i)
					.setBookCategory((bookCategoryDao.findById(bookDataList.get(i).getCategoryId()).orElse(null)));

		}
		return this.bookDao.getByBookId(bookId);
	}

}
