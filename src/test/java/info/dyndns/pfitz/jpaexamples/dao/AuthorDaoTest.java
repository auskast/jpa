package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath:context.xml")
public class AuthorDaoTest extends AbstractTestNGSpringContextTests {
    @Resource
    private AuthorDao authorDao;

    @Test
    public void testSave() throws Exception {
        final Author author = authorDao.save(new Author("Kendra", "Fitzgerald"));
        assertNotNull(author.getId());
        assertEquals(author.getId(), Integer.valueOf(1));
    }

    @Test(dependsOnMethods = "testSave")
    public void testSaveAgain() throws Exception {
        final Author original = authorDao.findById(1);
        original.setFirstName("Patrick");
        final Author result = authorDao.save(original);
        assertEquals(result, original);
    }

    @Test(dependsOnMethods = "testSaveAgain")
    public void testFindById() throws Exception {
        final Author author = authorDao.findById(1);
        assertEquals(author, createAuthor(1, "Patrick", "Fitzgerald"));
    }

    @Test(dependsOnMethods = "testSaveAgain")
    public void testFindByLastName() throws Exception {
        authorDao.save(new Author("Esther", "Lee"));
        authorDao.save(new Author("Karen", "Fitzgerald"));
        final List<Author> authors = authorDao.findByLastName("Fitzgerald");
        assertEquals(authors, newArrayList(
                createAuthor(1, "Patrick", "Fitzgerald"),
                createAuthor(3, "Karen", "Fitzgerald")
        ));
    }

    @Test(dependsOnMethods = "testFindByLastName")
    public void testGetAll() throws Exception {
        final List<Author> authors = authorDao.getAll();
        assertEquals(authors, newArrayList(
                createAuthor(1, "Patrick", "Fitzgerald"),
                createAuthor(2, "Esther", "Lee"),
                createAuthor(3, "Karen", "Fitzgerald")
        ));
    }

    @Test(dependsOnMethods = "testGetAll")
    public void testDelete() throws Exception {
        final List<Author> authors = authorDao.findByLastName("Fitzgerald");
        for (final Author author : authors) {
            authorDao.delete(author);
        }
        assertEquals(authorDao.getAll(), newArrayList(createAuthor(2, "Esther", "Lee")));
    }

    private Author createAuthor(Integer id, String firstName, String lastName) {
        final Author author = new Author(firstName, lastName);
        author.setId(id);

        return author;
    }
}
