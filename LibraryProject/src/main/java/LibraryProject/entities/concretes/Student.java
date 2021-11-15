package LibraryProject.entities.concretes;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "student_id")
	private long studentId;

	@NotNull
	@NotBlank
	@Column(name = "student_firstname")
	private String studentFirstname;

	@NotNull
	@NotBlank
	@Column(name = "student_lastname")
	private String studentLastname;

	@NotNull
	@NotBlank
	@Column(name = "nationality_id")
	private String nationalityId;

	@NotNull
	@NotBlank
	@Column(name = "birth_year")
	private int birthYear;

	@NotNull
	@NotBlank
	@Column(name = "gender")
	private String gender;

	@Column(name = "late_quantity") // Geç getirdiği kitap sayısı
	private Integer lateQuantity;

	@Column(name = "number_of_books") // Şu anda elindeki kitap sayısı
	private Integer numberOfBooks;

	@Column(name = "gender_url")
	private String genderUrl;
	
}
