package br.com.luis.fernando.LiterAlura.service;

import br.com.luis.fernando.LiterAlura.model.Book;
import br.com.luis.fernando.LiterAlura.model.BookRecord;
import br.com.luis.fernando.LiterAlura.model.BookResultRecord;
import br.com.luis.fernando.LiterAlura.util.Fetch;
import org.yaml.snakeyaml.util.UriEncoder;

import java.util.List;

public class SearchBook {
    public static BookRecord searchBook(String title){
        Fetch fetch = new Fetch();
        String uri = "https://gutendex.com/books?search="+title;
        var book = fetch.get(uri);
        return DataConvert.getData(book.body(), BookResultRecord.class).results().get(0);
    }
}
