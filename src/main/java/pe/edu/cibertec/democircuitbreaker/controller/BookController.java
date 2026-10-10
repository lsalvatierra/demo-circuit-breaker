package pe.edu.cibertec.democircuitbreaker.controller;

import org.springframework.web.bind.annotation.*;
import pe.edu.cibertec.democircuitbreaker.service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    // Inyección de dependencias a través del constructor
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Acepta el ID en la URL y un parámetro fail para forzar errores a voluntad
    @GetMapping("/{id}")
    public String getBook(@PathVariable String id, @RequestParam(defaultValue = "false") boolean fail) {
        return bookService.getBook(id, fail);
    }
}
