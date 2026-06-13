package com.example.musicstore.converter;

import com.example.musicstore.model.Genre;
import com.example.musicstore.repository.GenreRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToGenreConverter implements Converter<String, Genre> {

    private final GenreRepository genreRepository;

    public StringToGenreConverter(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public Genre convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }

        return genreRepository.findById(Long.valueOf(source))
                .orElseThrow(() -> new IllegalArgumentException("Genre not found: " + source));
    }
}
