package info.dyndns.pfitz.jpaexamples.dao.jpa;

import info.dyndns.pfitz.jpaexamples.dao.AuthorDao;
import info.dyndns.pfitz.jpaexamples.model.Author;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@NamedQueries({
        @NamedQuery(name = "findByLastName", query = "select authors from Author authors where last_name = :lastName"),
        @NamedQuery(name = "getAll", query = "select authors from Author authors")
})

public class AuthorDaoJpaImpl implements AuthorDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Integer save(Author author) {
        entityManager.persist(author);
        return author.getId();
    }

    @Override
    @Transactional(readOnly = true)
    public Author findById(Integer id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> findByLastName(String lastName) {
        final TypedQuery<Author> query = entityManager.createNamedQuery("findByLastName", Author.class);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Author> getAll() {
        return entityManager.createNamedQuery("getAll", Author.class).getResultList();
    }

    @Override
    @Transactional
    public void delete(Author author) {
        entityManager.remove(author);
    }
}
