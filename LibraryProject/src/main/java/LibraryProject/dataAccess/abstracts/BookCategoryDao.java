package LibraryProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import LibraryProject.entities.concretes.BookCategory;

public interface BookCategoryDao extends JpaRepository<BookCategory, Long> {
	BookCategory getByCategoryId(long categoryId);
}
