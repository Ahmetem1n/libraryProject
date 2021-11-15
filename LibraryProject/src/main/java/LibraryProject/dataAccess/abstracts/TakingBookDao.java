package LibraryProject.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;

import LibraryProject.entities.concretes.TakingBook;

public interface TakingBookDao extends JpaRepository<TakingBook, Long> {
	TakingBook getByTakingBookId(long id);
}
