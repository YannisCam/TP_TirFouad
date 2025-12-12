package RakCam.TP.controller;

import RakCam.TP.domain.Category;
import RakCam.TP.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/stats")
public class StatsController {

    private final BookService bookService;

    public StatsController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/books-per-category")
    public Map<Category, Long> getBooksByCategory() {
        return bookService.getBooksByCategory();
    }

    @GetMapping("/top-authors")
    public List<Object[]> getTopAuthors(@RequestParam(defaultValue = "3") int limit) {
        return bookService.getTopAuthors(limit);
    }
}
