package br.com.luis.fernando.LiterAlura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public record BookResultRecord(List<BookRecord> results) {
}
