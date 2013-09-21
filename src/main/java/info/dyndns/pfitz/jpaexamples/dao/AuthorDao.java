package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;

import java.util.List;

public interface AuthorDao {
    public Integer save(Author author);

    public Author findById(Integer id);

    public List<Author> findByLastName(String lastName);

    public List<Author> getAll();

    public void delete(Author author);
}
