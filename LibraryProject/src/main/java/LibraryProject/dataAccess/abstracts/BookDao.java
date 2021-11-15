package LibraryProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import LibraryProject.entities.concretes.Book;

public interface BookDao extends JpaRepository<Book, Long> {
	Book getByBookId(long bookId);
}
