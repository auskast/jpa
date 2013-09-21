package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;
import info.dyndns.pfitz.jpaexamples.model.Book;

import java.util.List;

public interface BookDao {
    public String save(Book book);

    public Book findByIsbn(String isbn);

    public List<Book> findByTitle(String title);

    public List<Book> findByAuthor(Author author);

    public List<Book> getAll();
}
