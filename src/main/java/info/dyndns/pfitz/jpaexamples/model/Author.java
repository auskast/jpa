package info.dyndns.pfitz.jpaexamples.model;

import com.google.common.base.Objects;

import javax.persistence.*;

@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "findByLastName", query = "from Author where last_name = :lastName"),
        @NamedQuery(name = "getAllAuthors", query = "from Author")
})
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Integer id;

    @Column(name = "first_name", length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    public Author() {
    }

    public Author(String lastName) {
        this.lastName = lastName;
    }

    public Author(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, lastName);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Author)) return false;
        final Author author = (Author) o;

        return Objects.equal(id, author.id) &&
                Objects.equal(firstName, author.firstName) &&
                Objects.equal(lastName, author.lastName);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .toString();
    }
}
