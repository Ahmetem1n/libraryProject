package LibraryProject.business.abstracts;

import java.util.List;

import LibraryProject.entities.concretes.BookCategory;

public interface BookCategoryService {
	void add(BookCategory bookCategory);

	List<BookCategory> getAll();
	
	BookCategory getByCategoryId(long categoryId);
}
