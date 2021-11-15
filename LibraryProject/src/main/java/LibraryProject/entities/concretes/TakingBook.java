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
@Table(name = "taking_books")
public class TakingBook {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long takingBookId;

	@Column(name = "take_date")
	private String takeDate;
	
	@Column(name="return_date")
	private String returnDate;

	@NotNull
	@NotBlank
	@Column(name = "book_id")
	private long bookId;

	@NotNull
	@NotBlank
	@Column(name = "student_id")
	private long studentId;
	
	@Transient
	private Book book;
	
	@Transient
	private Student student;

}
