package com.niit.jdp.repository;
import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseService;
import com.niit.jdp.service.GenreNotFound;
import com.niit.jdp.service.SongI;
import com.niit.jdp.service.SongNotFound;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRepository implements SongI {
    /*
     * 1.Display all song
     * 2.Search Song by song name.
     * 3.Search song by album name
     * 4.Search song by artist name
     * 5.Search song by genre
     */
    // Creating Database Service object for calling connect method
    DatabaseService databaseService = new DatabaseService();

    // display all the song from dataBase
    public List<Song> displayAllSong() {

        //creating object of song class (genericList)
        List<Song> songList = new ArrayList<>();
        //Creates a Statement object for sending SQL statements to the database.
        // SQL statements without parameters are normally executed using Statement objects.
        Connection connection = databaseService.connect();
        String sqlQuery = "SELECT * FROM song;";
        try {
            //PreparedStatement interface
            //Executes the SQL query in this PreparedStatement object
            // and returns the ResultSet object generated by the query.
            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();
            //using next method for control the loop
            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String duration = resultSet.getString(3);
                String albumName = resultSet.getString(4);
                String artistName = resultSet.getString(5);
                String genre = resultSet.getString(6);
                //we are adding the object of the song list
                songList.add(new Song(id, name, duration, albumName, artistName, genre));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return songList;
    }

    // search song by name
    public List<Song> getSongSearchBySongName(List<Song> songList, String name) throws SongNotFound {
        //Creates a Statement object for sending SQL statements to the database.
        // SQL statements without parameters are normally executed using Statement objects.
        Connection connection = databaseService.connect();
        List<Song> songList1 = new ArrayList<>();
        for (Song song : songList) {
            if (song.getName().equalsIgnoreCase(name)) {
                songList1.add(song);
            }
        }
        if (songList1.size() == 0) {
            //assigning value to constructor
            throw new SongNotFound("song not found");

        } else {
            return songList1;
        }
    }

    //search song by albumName
    public List<Song> getSongSearchByAlbumName(List<Song> songList, String albumName) {
        Connection connection = databaseService.connect();
        List<Song> songList1 = new ArrayList<>();
        for (Song song : songList) {
            if (song.getAlbumName().equalsIgnoreCase(albumName)) {
                songList1.add(song);

            } else {

            }
        }
        return songList1;
    }
    //search song by artist name
    public List<Song> getSongSearchByArtistName(List<Song> songList, String artistName) {
        Connection connection = databaseService.connect();
        List<Song> songList1 = new ArrayList<>();
        for (Song song : songList) {
            if (song.getArtistName().equalsIgnoreCase(artistName)) {
                songList1.add(song);
            }
        }
        return songList1;
    }

    //search song by genre
    public List<Song> getSongSearchByGenre(List<Song> songList, String genre) throws GenreNotFound {
        Connection connection = databaseService.connect();
        List<Song> songList1 = new ArrayList<>();
        for (Song song : songList) {
            if (song.getGenre().equalsIgnoreCase(genre)) {
                songList1.add(song);
            }
        }
        if (songList1.size() == 0) {
            //assigning value to constructor
            throw new GenreNotFound("Genre not found");
        } else {
            return songList1;
        }
    }

    public List<Song> sortSongs(List<Song> songList) {
        songList.sort((o1, o2) -> o1.getName().compareTo(o2.getName()));
        return songList;
    }
}