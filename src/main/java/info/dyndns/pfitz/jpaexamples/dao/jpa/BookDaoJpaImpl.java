package info.dyndns.pfitz.jpaexamples.dao.jpa;

import info.dyndns.pfitz.jpaexamples.dao.BookDao;
import info.dyndns.pfitz.jpaexamples.model.Author;
import info.dyndns.pfitz.jpaexamples.model.Book;

import java.util.List;

public class BookDaoJpaImpl implements BookDao {
    @Override
    public String save(Book book) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Book findByIsbn(String isbn) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Book> findByTitle(String title) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Book> getAll() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
