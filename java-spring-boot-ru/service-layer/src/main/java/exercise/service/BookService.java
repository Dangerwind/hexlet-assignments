package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.mapper.BookMapper;
import exercise.model.Book;
import exercise.repository.AuthorRepository;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    // BEGIN

    /*
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorMapper authorMapper;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;
*/
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private BookRepository bookRepository;


    public BookDTO createBook(BookCreateDTO bookCreateDTO) {
        var book = bookMapper.map(bookCreateDTO);
        bookRepository.save(book);
        return bookMapper.map(book);
    }

    public BookDTO updateBook(BookUpdateDTO bookUpdateDTO, Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        bookMapper.update(bookUpdateDTO, book);
        bookRepository.save(book);
        return bookMapper.map(book);

    }

    public  List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        var res = books.stream()
                .map(book -> bookMapper.map(book))
                .toList();
        return res;

    }

    public BookDTO getBookById(Long id) {
        var book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found"));
        return bookMapper.map(book);
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    
    // END
}
