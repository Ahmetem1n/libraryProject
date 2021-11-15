package LibraryProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LibraryProject.business.abstracts.TakingBookService;
import LibraryProject.entities.concretes.TakingBook;

@RestController
@RequestMapping("/takingBook/")
@CrossOrigin
public class TakingBookController {
	private TakingBookService takingBookService;

	@Autowired
	public TakingBookController(TakingBookService takingBookService) {
		super();
		this.takingBookService = takingBookService;
	}

	@PostMapping("take")
	public void take(TakingBook takingBook) {
		this.takingBookService.take(takingBook);
	}

	@PostMapping("give")
	public void give(TakingBook takingBook) {
		this.takingBookService.give(takingBook);
	}

	@GetMapping("getAll")
	public List<TakingBook> getAll() {
		return this.takingBookService.getAll();
	}

	@GetMapping("lateGetAll")
	public List<TakingBook> lateGetAll() {
		return this.takingBookService.lateGetAll();
	}

	@GetMapping("getByTakingBookId")
	public TakingBook getByTakingBookId(long id) {
		return this.takingBookService.getByTakingBookId(id);
	}
}
