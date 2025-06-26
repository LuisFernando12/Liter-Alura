package br.com.luis.fernando.LiterAlura.repository;

import br.com.luis.fernando.LiterAlura.enums.Language;
import br.com.luis.fernando.LiterAlura.model.Author;
import br.com.luis.fernando.LiterAlura.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("SELECT a FROM Book b JOIN b.authors a")
    List<Author> findAuthors();

    @Query("SELECT a, b FROM Book b JOIN b.authors a WHERE a.birthYear <= :birthYear AND :birthYear < a.deathYear ")
    List<Author> findAuthorsLiving(Integer birthYear);

//    List<Book> findByAuthorsBirthYearLessThanEqual(Integer birthYear);

    List<Book> findByLanguage(List<Language> language);

    List<Book> findTop10ByOrderByDownloadsNumberDesc();

    @Query("SELECT a, b FROM Book b JOIN b.authors a WHERE a.name ILIKE %:name%")
    Optional<Author> findAuthorByName(String name);

    @Query("SELECT a, b FROM Book b JOIN b.authors a WHERE a.deathYear = :deathYear")
    List<Author> findAuthorByDeathYear(Integer deathYear);
}
