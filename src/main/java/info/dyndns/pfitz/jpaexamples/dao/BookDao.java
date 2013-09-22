package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;
import info.dyndns.pfitz.jpaexamples.model.Book;

import java.util.List;

public interface BookDao {
    public <S extends Book> S save(S book);

    public Book findByIsbn(String isbn);

    public List<Book> findByTitle(String title);

    public List<Book> findByAuthor(Author author);

    public List<Book> getAll();

    public <S extends Book> void delete(S book);
}
