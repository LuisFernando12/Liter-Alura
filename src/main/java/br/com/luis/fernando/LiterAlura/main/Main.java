package br.com.luis.fernando.LiterAlura.main;

import br.com.luis.fernando.LiterAlura.enums.Language;
import br.com.luis.fernando.LiterAlura.model.Author;
import br.com.luis.fernando.LiterAlura.model.Book;
import br.com.luis.fernando.LiterAlura.repository.BookRepository;
import br.com.luis.fernando.LiterAlura.service.SearchBook;

import java.util.*;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private BookRepository bookRepository;
    public Main(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void showMenu(){
        String menu = """
                    1 - Search Book By Title
                    2 - List Registered Books
                    3 - List Registered Authors
                    4 - List Living Authors By Year 
                    5 - List Books By Language
                    6 - Top 10 Most Downloaded Books
                    7 - Find Author By Name
                    8 - Find Year By Death Year 
                    
                    0 - Exit
                    
                    Type a option: 
                """;
        boolean exit = false;
        
        
        while (!exit) {
            System.out.println(menu);
            Integer option = this.scanner.nextInt();
            this.scanner.nextLine();

            switch (option) {
                case 0:
                    System.out.println(" Bye Bye ");
                    exit = true;
                    System.exit(0);
                    break;
                case 1:
                    searchBookByTitle();
                    break;
                case 2:
                    listRegisteredBooks();
                    break;
                case 3:
                    listRegisteredAuthors();
                    break;
                case 4:
                    listLivingAuthorByYear();
                    break;
                case 5:
                    listBooksByLanguage();
                    break;
                case 6:
                    top10MostDownloadedBooks();
                    break;
                case 7:
                    findAuthorByName();
                    break;
                case 8:
                    findAuthorByDeathYear();
                    break;
                default:
                    System.out.println("Invalid Option");
                    break;
            }
        }
    }

    private void searchBookByTitle() {
        System.out.println("Type a Title Book: ");
        var titleBook = this.scanner.nextLine();
        var bookRecord = SearchBook.searchBook(titleBook);

        Book book = new Book(bookRecord);
        List<Author> authors = new ArrayList<>();

        bookRecord.authorRecord().forEach(authorRecord ->{
            Optional<Author> authorOptional = this.bookRepository.findAuthorByName(authorRecord.name());
            if (authorOptional.isPresent()){
                Author exixtingAuthor = authorOptional.get();
                exixtingAuthor.getBooks().add(book);
                authors.add(exixtingAuthor);
            }else {
                Author author = new Author(authorRecord);
                author.getBooks().add(book);
                authors.add(author);
            }
        });
        book.setAuthors(authors);
        this.bookRepository.save(book);
        System.out.println("Good");
    }

    private void listRegisteredBooks() {
        var books =  this.bookRepository.findAll();
        System.out.println("\n**********************************\n");
        books.forEach(book -> System.out.println("Book: %s, Languages: %s, Authors: %s, Downloads: %s ".formatted(
                book.getTitle(), book.getLanguage(),
                book.getAuthors(), book.getDownloadsNumber())));
        System.out.println("\n**********************************\n");
    }

    private void listRegisteredAuthors() {
        var authors = this.bookRepository.findAuthors();
        System.out.println("\n**********************************\n");
        authors.forEach(author -> System.out.println("Author: %s, BirthYear: %s, DeathYear: %s".formatted(
                author.getName(), author.getBirthYear(), author.getDeathYear()
        )));
        System.out.println("\n**********************************\n");
    }

    private void listLivingAuthorByYear() {
        System.out.println("Enter a year to find authors who were alive in this year");
        var year = this.scanner.nextInt();
        this.scanner.nextLine();

        var authors = this.bookRepository.findAuthorsLiving(year);
        System.out.println("\n**********************************\n");
        authors.forEach(author -> System.out.println("Author: %s, BirthYear: %s, DeathYear: %s".formatted(
                author.getName(), author.getBirthYear(), author.getDeathYear()
        )));
        System.out.println("\n**********************************\n");

    }

    private void listBooksByLanguage() {
        System.out.println("Enter a language to find books: ex: ("+ Language.showLanguage() +" )");
        var language = this.scanner.nextLine();
        List<Book> bookList = this.bookRepository.findByLanguage(Collections.singletonList(Language.languageFromString(language)));
        System.out.println("\n**********************************\n");
           bookList.forEach(book -> System.out.println("Book: %s, Languages: %s, Authors: %s, Downloads: %s ".formatted(
                    book.getTitle(), book.getLanguage(),
                    book.getAuthors(), book.getDownloadsNumber())));
        System.out.println("\n**********************************\n");
    }
    private void findAuthorByDeathYear() {
        System.out.println("Enter with the author death year");
        var deathYear = this.scanner.nextInt();
        this.scanner.nextLine();

        List<Author> authorList = this.bookRepository.findAuthorByDeathYear(deathYear);
        System.out.println("\n**********************************\n");
        authorList.forEach(author -> System.out.println("Author: %s, BirthYear: %s, DeathYear: %s".formatted(
                author.getName(), author.getBirthYear(), author.getDeathYear()
        )));
        System.out.println("\n**********************************\n");
    }

    private void findAuthorByName() {
        System.out.println("Enter with a author name: ");
        var authorName = this.scanner.nextLine();
        Optional<Author> author = this.bookRepository.findAuthorByName(authorName);
        if (!author.isPresent()) {
            System.out.println("Author not found");
        }
        System.out.println("\n**********************************\n");
        System.out.println(author);
        System.out.println("\n**********************************\n");
    }

    private void top10MostDownloadedBooks() {
        List<Book> bookList = this.bookRepository.findTop10ByOrderByDownloadsNumberDesc();
        System.out.println("\n**************************************************\n");
        bookList.forEach(book -> System.out.println("Book: %s, Languages: %s, Authors: %s, Downloads: %s ".formatted(
                book.getTitle(), book.getLanguage(),
                book.getAuthors(), book.getDownloadsNumber())));
        System.out.println("\n**************************************************\n");
    }
}
