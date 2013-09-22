package info.dyndns.pfitz.jpaexamples.dao.jpa;

import info.dyndns.pfitz.jpaexamples.dao.AuthorDao;
import info.dyndns.pfitz.jpaexamples.model.Author;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional(readOnly = true)
public class AuthorDaoJpaImpl implements AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public <S extends Author> S save(S author) {
        if (author.getId() == null) {
            entityManager.persist(author);
            return author;
        } else {
            return entityManager.merge(author);
        }
    }

    @Override
    public Author findById(Integer id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public List<Author> findByLastName(String lastName) {
        final TypedQuery<Author> query = entityManager.createNamedQuery("findByLastName", Author.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    public List<Author> getAll() {
        return entityManager.createNamedQuery("getAllAuthors", Author.class).getResultList();
    }

    @Override
    @Transactional
    public <S extends Author> void delete(S author) {
        final Author remove = entityManager.find(Author.class, author.getId());
        if (remove != null) {
            entityManager.remove(remove);
        }
    }
}
