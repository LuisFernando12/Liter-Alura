package br.com.luis.fernando.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AuthorRecord(String name, @JsonAlias("birth_year") Integer birthYear, @JsonAlias("death_year") Integer deathYear) {
}
