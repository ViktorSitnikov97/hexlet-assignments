package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
//import jakarta.validation.Valid;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<BookDTO> index() {
        var books = bookService.getAll();
        return books;
    }

    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO show(@PathVariable Long id) {
        var bookDTO = bookService.findById(id);
        return bookDTO;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BookDTO create(@RequestBody BookCreateDTO dto) {
        var bookDTO = bookService.create(dto);
        return bookDTO;
    }

    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BookDTO update(@RequestBody BookUpdateDTO dto, @PathVariable Long id) {
        var bookDTO = bookService.update(dto, id);
        return bookDTO;
    }

    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void destroy(@PathVariable Long id) {
        bookService.delete(id);
    }
    // END
}
