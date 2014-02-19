package info.dyndns.pfitz.jpaexamples.model;

import com.google.common.base.Objects;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "findByIsbn", query = "from Book where isbn = :isbn"),
        @NamedQuery(name = "findByTitle", query = "from Book where title = :title"),
        @NamedQuery(name = "findByAuthor", query = "from Book book join fetch book.authors author where author = :author"),
        @NamedQuery(name = "getAllBooks", query = "from Book")
})
public class Book {
    @Id
    @Column(name = "isbn", length = 50)
    private String isbn;

    @Column(name = "title")
    private String title;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "book_authors", joinColumns = {
            @JoinColumn(name = "books_isbn", referencedColumnName = "isbn")}, inverseJoinColumns = {
            @JoinColumn(name = "authors_author_id", referencedColumnName = "author_id")})
    private List<Author> authors = newArrayList();

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "num_pages")
    private Integer pages;

    @Column(name = "publish_date")
    private Date publishDate;

    public Book() {
    }

    public Book(String isbn, String title) {
        this.isbn = isbn;
        this.title = title;
    }

    public Book(String isbn, String title, Author... authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors = newArrayList(authors);
    }

    public Book(String isbn, String title, String description, Integer pages, Date publishDate, Author... authors) {
        this.isbn = isbn;
        this.title = title;
        this.authors = newArrayList(authors);
        this.description = description;
        this.pages = pages;
        this.publishDate = publishDate;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void addAuthor(Author author) {
        if (this.authors.contains(author)) {
            return;
        }
        this.authors.add(author);
    }

    public void removeAuthor(Author author) {
        if (!this.authors.contains(author)) {
            return;
        }
        this.authors.remove(author);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn, title, authors, description, pages, publishDate);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Book)) return false;
        final Book book = (Book) o;

        return Objects.equal(isbn, book.isbn) &&
                Objects.equal(title, book.title) &&
                authors.containsAll(book.authors) &&
                book.authors.containsAll(authors) &&
                Objects.equal(description, book.description) &&
                Objects.equal(pages, book.pages) &&
                Objects.equal(publishDate, book.publishDate);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("isbn", isbn)
                .add("title", title)
                .add("authors", authors)
                .add("description", description)
                .add("pages", pages)
                .add("publishDate", publishDate)
                .toString();
    }
}
