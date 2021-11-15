package LibraryProject.business.abstracts;

import java.util.List;

import LibraryProject.entities.concretes.Book;

public interface BookService {
	void add(Book book);
	List <Book> getAll();
	Book getByBookId(long bookId);
}
