package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.model.Author;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {
    // BEGIN

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    /*
    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    @Autowired
    private BookMapper bookMapper;
*/
    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();
        var res = authors.stream()
                .map(authorMapper::map)
                .toList();
        return res;
    }

    public AuthorDTO createAuthor(AuthorCreateDTO authorCreateDTO) {
        Author author = authorMapper.map(authorCreateDTO);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO updateAuthor(AuthorUpdateDTO authorUpdateDTO, Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        authorMapper.update(authorUpdateDTO,author);
        authorRepository.save(author);
        return authorMapper.map(author);
    }

    public AuthorDTO getAuthorById(Long id) {
        var author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
        return authorMapper.map(author);

    }

    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }
    
    // END
}
