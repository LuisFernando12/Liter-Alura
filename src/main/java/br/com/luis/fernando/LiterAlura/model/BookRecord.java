package br.com.luis.fernando.LiterAlura.model;

import br.com.luis.fernando.LiterAlura.enums.Language;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookRecord(
        String title,
        @JsonAlias("authors") List<AuthorRecord> authorRecord,
        List<String> languages,
        @JsonAlias("download_count") Integer downloadCount
) {}
