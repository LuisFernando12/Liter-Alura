package br.com.luis.fernando.LiterAlura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
    private  Integer birthYear;
    private  Integer deathYear;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(AuthorRecord authorRecord) {
        this.name = authorRecord.name();
        this.birthYear = authorRecord.birthYear();
        this.deathYear = authorRecord.deathYear();
    }

//    public Author(Author author) {

//        this.name = author.getName();
//        this.birthYear = author.getBirthYear();
//        this.deathYear = author.getDeathYear();
//    }
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public String toString() {
        return "{ name='" + name + '\'' +
                ", birthYear=" + birthYear +
                ", deathYear=" + deathYear +
                '}';
    }
}
