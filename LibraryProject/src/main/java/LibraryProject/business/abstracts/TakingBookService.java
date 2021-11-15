package LibraryProject.business.abstracts;

import java.util.List;

import LibraryProject.entities.concretes.TakingBook;

public interface TakingBookService {
	void take(TakingBook takingBook);

	void give(TakingBook takingBook);
	
	List<TakingBook> getAll();

	List<TakingBook> lateGetAll();
	
	TakingBook getByTakingBookId(long id);
}
