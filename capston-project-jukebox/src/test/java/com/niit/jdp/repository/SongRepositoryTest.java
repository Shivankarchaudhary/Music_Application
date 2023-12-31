package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.GenreNotFound;
import com.niit.jdp.service.SongNotFound;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class SongRepositoryTest {
    SongRepository songRepository;
    List<Song> songList;

    @BeforeEach
    void setUp() {
        songRepository = new SongRepository();
        songList = songRepository.displayAllSong();
    }

    @AfterEach
    void tearDown() {
        songRepository = null;
    }




    @Test
    void getSongSearchByAlbumNames() {
        int expected = 1;
        int unexpected = 5;
        List<Song> getSongSearchByAlbumName = songRepository.getSongSearchByAlbumName(songList, "Random");
        int actual = getSongSearchByAlbumName.size();
        assertNotEquals(unexpected, actual);
    }



    @Test
    void getSongSearchByArtistNames() {
        int expected = 1;
        int unexpected = 5;
        List<Song> getSongSearchByArtistName = songRepository.getSongSearchByArtistName(songList, "Sia");
        int actual = getSongSearchByArtistName.size();
        assertNotEquals(unexpected, actual);


    }


    @Test
    void getSongSearchByGenre() {
        int expected = 1;
        int unexpected = 5;
        try {
            List<Song> getSongSearchByGenre = songRepository.getSongSearchByGenre(songList, "Romantic");
            int actual = getSongSearchByGenre.size();
            assertEquals(expected, actual);
        } catch (GenreNotFound e) {
            e.printStackTrace();
        }
    }

    @Test
    void getSongSearchByGenres() {
        int expected = 1;
        int unexpected = 5;
        try {
            List<Song> getSongSearchByGenre = songRepository.getSongSearchByGenre(songList, "Romantic");
            int actual = getSongSearchByGenre.size();
            assertNotEquals(unexpected, actual);
        } catch (GenreNotFound e) {
            e.printStackTrace();
        }
    }
}