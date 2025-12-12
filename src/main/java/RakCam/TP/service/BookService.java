package RakCam.TP.service;

import RakCam.TP.domain.Book;
import RakCam.TP.domain.Category;
import RakCam.TP.exception.ResourceNotFoundException;
import RakCam.TP.exception.DuplicateResourceException;
import RakCam.TP.repository.BookRepository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public Book createBook(Book book) {
        if (bookRepository.existsByIsbn(book.getIsbn())) {
            throw new DuplicateResourceException("ISBN déjà existant: " + book.getIsbn());
        }
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book book) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre non trouvé avec l'ID: " + id));
        
        existingBook.setTitle(book.getTitle());
        existingBook.setIsbn(book.getIsbn());
        existingBook.setYear(book.getYear());
        existingBook.setCategory(book.getCategory());
        existingBook.setAuthor(book.getAuthor());
        
        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Livre non trouvé avec l'ID: " + id);
        }
        bookRepository.deleteById(id);
    }

    public Map<Category, Long> getBooksByCategory() {
        List<Object[]> results = bookRepository.countBooksByCategory();
        Map<Category, Long> stats = new HashMap<>();
        
        for (Object[] row : results) {
            Category category = (Category) row[0];
            Long count = (Long) row[1];
            stats.put(category, count);
        }
        
        return stats;
    }

    public List<Object[]> getTopAuthors(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return bookRepository.findTopAuthors(pageable);
    }
}
