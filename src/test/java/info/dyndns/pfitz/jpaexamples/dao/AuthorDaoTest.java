package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;

import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertNotNull;

@ContextConfiguration(locations = "classpath:context.xml")
public class AuthorDaoTest extends AbstractTestNGSpringContextTests {
    @Resource
    private AuthorDao authorDao;

    @Test
    public void testSave() throws Exception {
        final Author author = new Author();
        author.setFirstName("Patrick");
        author.setLastName("Fitzgerald");
        authorDao.save(author);
        assertNotNull(author.getId());
        assertNotEquals(author.getId(), 0);
    }
}
