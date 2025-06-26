package br.com.luis.fernando.LiterAlura.util;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public interface IFetch {
    HttpResponse<String> get(String uri);
}
