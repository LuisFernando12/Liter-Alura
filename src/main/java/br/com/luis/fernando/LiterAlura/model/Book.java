package br.com.luis.fernando.LiterAlura.model;

import br.com.luis.fernando.LiterAlura.enums.Language;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<Author> authors = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private List<Language> language;
    private Integer downloadsNumber;

    public Book(){}
    public Book(BookRecord bookRecord) {
             this.title = bookRecord.title();
             this.language = bookRecord.languages().stream().map(lang-> Language.valueOf(lang.toUpperCase())).toList();
             this.downloadsNumber = bookRecord.downloadCount();
    }


    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Language> getLanguage() {
        return language;
    }

    public Integer getDownloadsNumber() {
        return downloadsNumber;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "downloadsNumber=" + downloadsNumber +
                ", language=" + language +
                ", authors=" + authors +
                ", title='" + title + '\'' +
                ", id=" + id +
                '}';
    }
}
