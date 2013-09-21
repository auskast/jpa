package info.dyndns.pfitz.jpaexamples.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "authors")
@NamedQueries({
        @NamedQuery(name = "findByLastName", query = "from Author where last_name = :lastName"),
        @NamedQuery(name = "getAll", query = "from Author")
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
    public boolean equals(Object o) {
        if (!(o instanceof Author)) return false;
        final Author author = (Author) o;

        if (id != null ? !id.equals(author.id) : author.id == null) return false;
        if (firstName != null ? !firstName.equals(author.firstName) : author.firstName == null) return false;
        if (lastName != null ? !lastName.equals(author.lastName) : author.lastName == null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int hashCode = id.hashCode();

        hashCode = 31 * hashCode + firstName.hashCode();
        hashCode = 31 * hashCode + lastName.hashCode();

        return hashCode;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
