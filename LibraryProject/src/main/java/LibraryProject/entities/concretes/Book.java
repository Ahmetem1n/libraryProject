package LibraryProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")

public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private long bookId;

	@NotNull
	@NotBlank
	@Column(name = "book_name")
	private String bookName;

	@NotNull
	@NotBlank
	@Column(name = "book_summary")
	private String bookSummary;

	@NotNull
	@NotBlank
	@Column(name = "book_author")
	private String bookAuthor;

	@NotNull
	@NotBlank
	@Column(name = "publisher_name")
	private String publisherName;

	@NotNull
	@NotBlank
	@Column(name = "publication_year")
	private int publicationYear;

	@NotNull
	@NotBlank
	@Column(name = "page_number")
	private int pageNumber;

	@NotNull
	@NotBlank
	@Column(name = "stock_quantity")
	private int stockQuantity;

	@Column(name = "image_url")
	private String imageUrl;

	@NotNull
	@NotBlank
	@Column(name = "barcode")
	private String barcode;

	@NotNull
	@NotBlank
	@Column(name = "category_id")
	private long categoryId;

	@Transient
	private BookCategory bookCategory;

}
