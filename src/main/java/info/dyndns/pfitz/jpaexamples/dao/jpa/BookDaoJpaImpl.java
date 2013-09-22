package info.dyndns.pfitz.jpaexamples.dao.jpa;

import info.dyndns.pfitz.jpaexamples.dao.AuthorDao;
import info.dyndns.pfitz.jpaexamples.dao.BookDao;
import info.dyndns.pfitz.jpaexamples.model.Author;
import info.dyndns.pfitz.jpaexamples.model.Book;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional(readOnly = true)
public class BookDaoJpaImpl implements BookDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Resource
    private AuthorDao authorDao;

    @Override
    @Transactional
    public <S extends Book> S save(S book) {
        if (book.getAuthors() != null) {
            for (final Author author : book.getAuthors()) {
                authorDao.save(author);
            }
        }

        if (entityManager.find(Book.class, book.getIsbn()) == null) {
            entityManager.persist(book);
            return book;
        } else {
            return entityManager.merge(book);
        }
    }

    @Override
    public Book findByIsbn(String isbn) {
        final TypedQuery<Book> query = entityManager.createNamedQuery("findByIsbn", Book.class);
        query.setParameter("isbn", isbn);
        return query.getSingleResult();
    }

    @Override
    public List<Book> findByTitle(String title) {
        final TypedQuery<Book> query = entityManager.createNamedQuery("findByTitle", Book.class);
        query.setParameter("title", title);
        return query.getResultList();
    }

    @Override
    public List<Book> findByAuthor(Author author) {
        final TypedQuery<Book> query = entityManager.createNamedQuery("findByAuthor", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }

    @Override
    public List<Book> getAll() {
        return entityManager.createNamedQuery("getAllBooks", Book.class).getResultList();
    }

    @Override
    @Transactional
    public <S extends Book> void delete(S book) {
        final Book remove = entityManager.find(Book.class, book.getIsbn());
        if (remove != null) {
            entityManager.remove(remove);
        }
    }
}
