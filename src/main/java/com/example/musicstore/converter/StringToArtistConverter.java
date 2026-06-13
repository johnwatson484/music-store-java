package com.example.musicstore.converter;

import com.example.musicstore.model.Artist;
import com.example.musicstore.repository.ArtistRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToArtistConverter implements Converter<String, Artist> {

    private final ArtistRepository artistRepository;

    public StringToArtistConverter(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Artist convert(String source) {
        if (source == null || source.isBlank()) {
            return null;
        }

        return artistRepository.findById(Long.valueOf(source))
                .orElseThrow(() -> new IllegalArgumentException("Artist not found: " + source));
    }
}
