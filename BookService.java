package com.challengealura.Literalura.service;

import com.challengealura.Literalura.model.Autor;
import com.challengealura.Literalura.model.Libro;
import com.challengealura.Literalura.repository.AutorRepository;
import com.challengealura.Literalura.repository.LibroRepository;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private LibroRepository libroRepository;

    @Autowired
    private AutorRepository autorRepository;

    private static final String BASE_URL = "https://gutendex.com/books?search=";

    public Libro buscarLibroPorTitulo(String titulo) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI(BASE_URL + titulo))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();
        ApiResponse apiResponse = mapper.readValue(response.body(), ApiResponse.class);

        if (apiResponse.results.isEmpty()) {
            return null;
        }

        Result result = apiResponse.results.get(0);

        Autor autor = new Autor();
        if (!result.authors.isEmpty()) {
            autor.setNombre(result.authors.get(0).name);
            autor.setYearNacimiento(result.authors.get(0).birth_year);
            autor.setYearFallecimiento(result.authors.get(0).death_year);

            autorRepository.save(autor);
        }

        Libro libro = new Libro();
        libro.setTitulo(result.title);
        libro.setIdioma(result.languages.get(0));
        libro.setDescargas(result.download_count);
        libro.setAutor(autor);

        libroRepository.save(libro);

        return libro;
    }

    public List<Autor> obtenerAutoresVivosEnYear(int year) {
        return autorRepository.findByYearNacimientoBeforeAndYearFallecimientoAfter(year, year);
    }

    public List<Libro> obtenerTodosLosLibros() {
        return libroRepository.findAll();
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class ApiResponse {
        public List<Result> results;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Result {
        public String title;
        @JsonAlias("languages")
        public List<String> languages;
        @JsonAlias("download_count")
        public int download_count;
        @JsonAlias("authors")
        public List<Author> authors;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Author {
        public String name;
        public int birth_year;
        public int death_year;
    }
}

