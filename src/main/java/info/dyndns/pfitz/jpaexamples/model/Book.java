package info.dyndns.pfitz.jpaexamples.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Entity
@Table(name = "books")
@NamedQueries({
        @NamedQuery(name = "findByIsbn", query = "from Book where isbn = :isbn"),
        @NamedQuery(name = "findByTitle", query = "from Book where title = :title"),
        @NamedQuery(name = "findByAuthor", query = "select book from Book book join fetch book.authors author where author = :author"),
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
        int hashCode = isbn == null ? 0 : isbn.hashCode();

        hashCode = 31 * hashCode + (title == null ? 0 : title.hashCode());
        hashCode = 31 * hashCode + (authors == null ? 0 : authors.hashCode());
        hashCode = 31 * hashCode + (description == null ? 0 : description.hashCode());
        hashCode = 31 * hashCode + (pages == null ? 0 : pages.hashCode());
        hashCode = 31 * hashCode + (publishDate == null ? 0 : publishDate.hashCode());

        return hashCode;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Book)) return false;
        final Book book = (Book) o;

        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (authors != null ? !(authors.containsAll(book.authors) && book.authors.containsAll(authors)) : book.authors != null) return false;
        if (description != null ? !description.equals(book.description) : book.description != null) return false;
        if (pages != null ? !pages.equals(book.pages) : book.pages != null) return false;
        if (publishDate != null ? !publishDate.equals(book.publishDate) : book.publishDate != null) return false;

        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
