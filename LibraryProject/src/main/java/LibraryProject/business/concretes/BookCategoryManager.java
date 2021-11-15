package LibraryProject.business.concretes;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import LibraryProject.business.abstracts.BookCategoryService;
import LibraryProject.dataAccess.abstracts.BookCategoryDao;
import LibraryProject.entities.concretes.BookCategory;

@Service
public class BookCategoryManager implements BookCategoryService {
	private BookCategoryDao bookCategoryDao;

	@Autowired
	public BookCategoryManager(BookCategoryDao bookCategoryDao) {
		super();
		this.bookCategoryDao = bookCategoryDao;
	}

	@Override
	public void add(BookCategory bookCategory) {
		List<BookCategory> bookCategoryDataList = this.bookCategoryDao.findAll();
		for (int i = 0; i <= bookCategoryDataList.size(); i++) {
			if (bookCategoryDataList.size() != 0
					&& bookCategoryDataList.get(i).getCategoryName().equalsIgnoreCase(bookCategory.getCategoryName())) {
				System.out.println("Bu kategori daha önce eklenmiş.");
				return;
			} else if (i == bookCategoryDataList.size() - 1 || bookCategoryDataList.size() == 0) {
				System.out.println("Kategori ekleme işlemi yapıldı.");
				this.bookCategoryDao.save(bookCategory);
				return;
			}
		}

	}

	@Override
	public List<BookCategory> getAll() {
		return this.bookCategoryDao.findAll();
	}

	@Override
	public BookCategory getByCategoryId(long categoryId) {
		return this.bookCategoryDao.getByCategoryId(categoryId);
	}
}
