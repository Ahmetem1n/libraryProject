package LibraryProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LibraryProject.business.abstracts.BookService;
import LibraryProject.entities.concretes.Book;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/book/")
@CrossOrigin
public class BooksController {

	private BookService bookService;

	@Autowired
	public BooksController(BookService bookService) {
		super();
		this.bookService = bookService;
	}

	@PostMapping("add")
	public void add(@RequestBody Book book) {
		this.bookService.add(book);
	}
	
	@GetMapping("getAll")
	public List<Book> getAll() {
		return this.bookService.getAll();
	}
	@GetMapping("getByBookId")
	public Book getByBookId(long bookId) {
		return this.bookService.getByBookId(bookId);
	}
}
