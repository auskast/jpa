package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;

import java.util.List;

public interface AuthorDao {
    public <S extends Author> S save(S author);

    public Author findById(Integer id);

    public List<Author> findByLastName(String lastName);

    public List<Author> getAll();

    public <S extends Author> void delete(S author);
}
