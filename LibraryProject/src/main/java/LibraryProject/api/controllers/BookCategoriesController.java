package LibraryProject.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import LibraryProject.business.abstracts.BookCategoryService;
import LibraryProject.entities.concretes.BookCategory;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/categories/")
@CrossOrigin
public class BookCategoriesController {
	private BookCategoryService bookCategoryService;

	@Autowired
	public BookCategoriesController(BookCategoryService bookCategoryService) {
		super();
		this.bookCategoryService = bookCategoryService;
	}

	@PostMapping("add")
	public void add(@RequestBody BookCategory bookCategory) {
		this.bookCategoryService.add(bookCategory);
	}

	@GetMapping("getAll")
	public List<BookCategory> getAll() {
		return this.bookCategoryService.getAll();
	}

	@GetMapping("getByCategoryId")
	public BookCategory getByCategoryId(long categoryId) {
		return this.bookCategoryService.getByCategoryId(categoryId);
	}
}
