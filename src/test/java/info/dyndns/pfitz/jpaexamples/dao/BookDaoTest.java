package info.dyndns.pfitz.jpaexamples.dao;

import info.dyndns.pfitz.jpaexamples.model.Author;
import info.dyndns.pfitz.jpaexamples.model.Book;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;
import static org.testng.Assert.assertEquals;

@ContextConfiguration(locations = "classpath:context.xml")
public class BookDaoTest extends AbstractTestNGSpringContextTests {
    @Resource
    private BookDao bookDao;

    @Test
    public void testSave() throws Exception {
        final Book book = bookDao.save(new Book("ABC-123", "My Book", "", 123, new Date(1234567890),
                new Author("Patrick", "Fitzgerald")));
        assertEquals(book.getAuthors().get(0).getId(), Integer.valueOf(1));
    }

    @Test(dependsOnMethods = "testSave")
    public void testSaveAgain() throws Exception {
        final Book original = bookDao.findByIsbn("ABC-123");
        original.setPages(456);
        final Book result = bookDao.save(original);
        assertEquals(result, original);
    }

    @Test(dependsOnMethods = "testSaveAgain")
    public void testFindByIsbn() throws Exception {
        final Book book = bookDao.findByIsbn("ABC-123");
        assertEquals(book, new Book("ABC-123", "My Book", "", 456, new Date(1234567890),
                createAuthor(1, "Patrick", "Fitzgerald")));
    }

    @Test(dependsOnMethods = "testSaveAgain")
    public void testFindByTitle() throws Exception {
        bookDao.save(new Book("DEF-456", "Other Book", createAuthor(1, "Patrick", "Fitzgerald")));
        bookDao.save(new Book("GHI-789", "My Book"));
        final List<Book> books = bookDao.findByTitle("My Book");
        assertEquals(books, newArrayList(
                new Book("ABC-123", "My Book", "", 456, new Date(1234567890), createAuthor(1, "Patrick", "Fitzgerald")),
                new Book("GHI-789", "My Book")
        ));
    }

    @Test(dependsOnMethods = "testFindByTitle")
    public void testFindByAuthor() throws Exception {
        final List<Book> books = bookDao.findByAuthor(createAuthor(1, "Patrick", "Fitzgerald"));
        assertEquals(books, newArrayList(
                new Book("ABC-123", "My Book", "", 456, new Date(1234567890), createAuthor(1, "Patrick", "Fitzgerald")),
                new Book("DEF-456", "Other Book", createAuthor(1, "Patrick", "Fitzgerald"))
        ));
    }

    @Test(dependsOnMethods = "testSaveAgain")
    public void testGetAll() throws Exception {
        final List<Book> books = bookDao.getAll();
        assertEquals(books, newArrayList(
                new Book("ABC-123", "My Book", "", 456, new Date(1234567890), createAuthor(1, "Patrick", "Fitzgerald")),
                new Book("DEF-456", "Other Book", createAuthor(1, "Patrick", "Fitzgerald")),
                new Book("GHI-789", "My Book")
        ));
    }

    private Author createAuthor(Integer id, String firstName, String lastName) {
        final Author author = new Author(firstName, lastName);
        author.setId(id);

        return author;
    }
}
