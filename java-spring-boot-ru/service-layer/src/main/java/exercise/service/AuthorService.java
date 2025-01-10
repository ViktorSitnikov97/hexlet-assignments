package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.validation.Validator;

import java.util.List;
import java.util.Set;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private Validator validator;

    public List<AuthorDTO> getAll() {
        var authors = authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::map)
                .toList();
    }

    public AuthorDTO findById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        var authorDTO = authorMapper.map(author);

        return authorDTO;
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public AuthorDTO create(AuthorCreateDTO dto) {
        validation(dto);
        var author = authorMapper.map(dto);
        authorRepository.save(author);
        var authorDTO = authorMapper.map(author);

        return authorDTO;
    }

    public AuthorDTO update(AuthorUpdateDTO dto, Long id) {
        validation(dto);
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author with id " + id + " not found"));
        authorMapper.update(dto, author);
        authorRepository.save(author);
        var authorDTO = authorMapper.map(author);

        return authorDTO;
    }

    // сделано в исследовательских целях :) Можно просто использовать @Valid в контроллере - выше сервиса
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
