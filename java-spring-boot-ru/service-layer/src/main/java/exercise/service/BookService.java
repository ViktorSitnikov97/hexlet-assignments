package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private Validator validator;

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<BookDTO> getAll() {
        var books = bookRepository.findAll();
        return books.stream()
                .map(bookMapper::map)
                .toList();
    }

    public BookDTO findById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        var bookDTO = bookMapper.map(book);

        return bookDTO;
    }

    public void delete(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        var author = authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Author with id " + book.getAuthor().getId() + " not found"
                ));
        bookRepository.deleteById(id);
        author.getBooks().remove(book);
    }

    public BookDTO create(BookCreateDTO dto) {
        validation(dto);
        var book = bookMapper.map(dto);
        bookRepository.save(book);
        var author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + dto.getAuthorId() + " not found"));
        author.getBooks().add(book);
        var authorDTO = bookMapper.map(book);

        return authorDTO;
    }

    public BookDTO update(BookUpdateDTO dto, Long id) {
        validation(dto);
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id " + id + " not found"));
        bookMapper.update(dto, book);
        bookRepository.save(book);
        var authorDTO = bookMapper.map(book);

        return authorDTO;
    }

    // сделано в исследовательских целях :)
    private <T> void validation(T dto) {
        Set<ConstraintViolation<T>> violations = validator.validate(dto);
        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation : violations) {
                sb.append(constraintViolation.getMessage());
            }
            throw new ConstraintViolationException("Error occurred: " + sb.toString(), violations);
        }
    }
    // END
}
