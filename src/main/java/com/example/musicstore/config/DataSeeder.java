package com.example.musicstore.config;

import com.example.musicstore.model.Album;
import com.example.musicstore.model.Artist;
import com.example.musicstore.model.Genre;
import com.example.musicstore.repository.AlbumRepository;
import com.example.musicstore.repository.ArtistRepository;
import com.example.musicstore.repository.GenreRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final GenreRepository genreRepository;
    private final ArtistRepository artistRepository;
    private final AlbumRepository albumRepository;

    public DataSeeder(
            GenreRepository genreRepository,
            ArtistRepository artistRepository,
            AlbumRepository albumRepository
    ) {
        this.genreRepository = genreRepository;
        this.artistRepository = artistRepository;
        this.albumRepository = albumRepository;
    }

    @Override
    public void run(String... args) {
        if (albumRepository.count() > 0) {
            return;
        }

        Genre rock = genreRepository.save(new Genre("Rock", "Guitars, drums, and loud choruses."));
        Genre jazz = genreRepository.save(new Genre("Jazz", "Improvisation, swing, and expressive solos."));
        Genre metal = genreRepository.save(new Genre("Metal", "Heavy riffs and powerful vocals."));
        Genre electronic = genreRepository.save(new Genre("Electronic", "Synths, beats, and digital soundscapes."));
        Genre classical = genreRepository.save(new Genre("Classical", "Orchestral and chamber music."));

        Artist queen = artistRepository.save(new Artist("Queen"));
        Artist miles = artistRepository.save(new Artist("Miles Davis"));
        Artist maiden = artistRepository.save(new Artist("Iron Maiden"));
        Artist daft = artistRepository.save(new Artist("Daft Punk"));
        Artist mozart = artistRepository.save(new Artist("Mozart"));

        albumRepository.save(new Album("A Night at the Opera", new BigDecimal("9.99"), "/images/album.png", rock, queen));
        albumRepository.save(new Album("Kind of Blue", new BigDecimal("8.99"), "/images/album.png", jazz, miles));
        albumRepository.save(new Album("The Number of the Beast", new BigDecimal("10.99"), "/images/album.png", metal, maiden));
        albumRepository.save(new Album("Discovery", new BigDecimal("7.99"), "/images/album.png", electronic, daft));
        albumRepository.save(new Album("Requiem", new BigDecimal("6.99"), "/images/album.png", classical, mozart));
        albumRepository.save(new Album("Jazz at Midnight", new BigDecimal("5.99"), "/images/album.png", jazz, miles));
    }
}
