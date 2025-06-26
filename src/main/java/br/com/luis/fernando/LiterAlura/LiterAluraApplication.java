package br.com.luis.fernando.LiterAlura;

import br.com.luis.fernando.LiterAlura.enums.Language;
import br.com.luis.fernando.LiterAlura.main.Main;
import br.com.luis.fernando.LiterAlura.repository.BookRepository;
import br.com.luis.fernando.LiterAlura.util.ConfigEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	public static void main(String[] args) {
		ConfigEnv.init();
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(Language.valueOf("EN"));
		Main main = new Main(bookRepository);
		main.showMenu();
	}
}
