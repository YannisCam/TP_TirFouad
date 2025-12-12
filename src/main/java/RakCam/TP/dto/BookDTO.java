package RakCam.TP.dto;

import RakCam.TP.domain.Author;
import RakCam.TP.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Pattern;

public class BookDTO {

    @NotBlank(message = "Le titre est obligatoire")
    private String title;

    @NotBlank(message = "L'ISBN est obligatoire")
    @Pattern(regexp = "^[0-9-]{10,17}$", message = "L'ISBN doit être au format correct (10 ou 13 chiffres avec tirets)")
    private String isbn;

    @NotNull(message = "L'année est obligatoire")
    @Min(value = 1450, message = "L'année doit être après 1450")
    @Max(value = 2024, message = "L'année ne peut pas être dans le futur")
    private Integer year;

    @NotNull(message = "La catégorie est obligatoire")
    private Category category;

    @NotNull(message = "L'auteur est obligatoire")
    private Author author;

    public BookDTO() {}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
