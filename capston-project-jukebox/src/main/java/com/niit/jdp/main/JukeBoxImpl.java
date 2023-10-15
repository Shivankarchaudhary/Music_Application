
package com.niit.jdp.main;

import com.niit.jdp.model.PlayList;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlayListRepository;
import com.niit.jdp.repository.SongRepository;
import com.niit.jdp.service.GenreNotFound;
import com.niit.jdp.service.SongNotFound;
import com.niit.jdp.service.SongService;

import java.util.List;
import java.util.Scanner;

public class JukeBoxImpl {
    public static void main(String[] args) throws SongNotFound {
        //used for blue colour in output
        System.out.println("\u001B[34m");
        //creating scanner object for take input from user

        Scanner scanner = new Scanner(System.in);
        // creating object of song repository class for call method
        SongRepository songRepository = new SongRepository();
        // creating object of playlist repository class for call method
        PlayListRepository playListRepository = new PlayListRepository();
        //creating object os song service class for call method
        SongService songService = new SongService();
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("-----------------------------------------------------WELCOME TO JUKEBOX---------------------------------------------------------");
        System.out.println("");
        System.out.println("--------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("");
        System.out.println("-----------------------------------------------------------All song-------------------------------------------------------------");
        System.out.println();
        //display method return value store this object
        List<Song> displayAllSong = songRepository.displayAllSong();
        displayFormat(displayAllSong);
        System.out.println();
        System.out.println("|-------------------------------------------------------------------------------------------------------------------------------|");
        System.out.println();
        int task = 0;
        do {
            System.out.println("Press 1 to search in list");
            System.out.println("Press 2 to Play a Song");
            System.out.println("Press 3 to create play list");
            System.out.println("Press 4 to insert song into play List");
            System.out.println("Press 5 to view play list");
            System.out.println("Press 6 for Sorted list");
            System.out.println("Press 7 to Exit");
            task = scanner.nextInt();
            System.out.println();
            System.out.println("---------------------------------------------------------------------------------------------------------------");
            System.out.println();
            switch (task) {
                case 1:
                    System.out.println("Press 1 to Search song details-by Genre");
                    System.out.println("Press 2 to Search song detail-by Album");
                    System.out.println("Press 3 to Search song detail by-Artist Name");
                    System.out.println("Press 4 to Search song detail by-Song Name");
                    int choice = scanner.nextInt();
                    System.out.println("------------------------------------------------------------------------");
                    if (choice == 1) {
                        System.out.println("english,hindi,bollyhood,punjabi");
                        System.out.println("Enter Genre name ::");
                        String genre = scanner.next();
                        scanner.nextLine();
                        List<Song> getGenre = null;
                        try {
                            getGenre = songRepository.getSongSearchByGenre(displayAllSong, genre);
                            displayFormat(getGenre);
                            System.out.println("If you want to play song then press (Y/N)");
                            String option = scanner.next();
                            if (option.equals("Y")) {
                                System.out.println("please enter the song id which you want play");
                                int id = scanner.nextInt();
                                songService.playParticular(id);
                                System.out.println();
                            } else {
                                break;
                            }
                        } catch (GenreNotFound e) {
                            System.out.println(e);
                        }

                    } else if (choice == 2) {
                        System.out.println("let me,the carnival,villagers,brahmastra,me");
                        System.out.println("Enter Album name ::");
                        String album = scanner.next();
                        scanner.nextLine();
                        List<Song> getAlbum = songRepository.getSongSearchByAlbumName(displayAllSong, album);
                        displayFormat(getAlbum);
                        System.out.println("If you want to play song then press (Y/N)");
                        String option = scanner.next();
                        if (option.equals("Y")) {
                            System.out.println("please enter the song id which you want play");
                            int id = scanner.nextInt();
                            songService.playParticular(id);
                            System.out.println();
                        } else {
                            break;
                        }
                    } else if (choice == 3) {
                        System.out.println("honey singn,zayn malik,arjit singh,king,raftaar");
                        System.out.println("Enter Artist Name ::");
                        String artistName = scanner.next();
                        scanner.nextLine();
                        List<Song> getArtist = songRepository.getSongSearchByArtistName(displayAllSong, artistName);
                        displayFormat(getArtist);
                        System.out.println("If you want to play song then press (Y/N)");
                        String option = scanner.next();
                        if (option.equals("Y")) {
                            System.out.println("please enter the song id which you want play");
                            int id = scanner.nextInt();
                            songService.playParticular(id);
                            System.out.println();
                        } else {
                            break;
                        }
                    } else if (choice == 4) {
                        System.out.println("let me,kesariya,fingers,entertaniner,paris ka trip,care ni karda,together forever,pablo,oops,maan meri jaan,no china,rashaan");
                        System.out.println("Enter Song Name ::");
                        scanner.nextLine();
                        String songName = scanner.nextLine();
                        List<Song> getSong = null;
                        try {
                            getSong = songRepository.getSongSearchBySongName(displayAllSong, songName);
                            displayFormat(getSong);
                            System.out.println("If you want to play song then press (Y/N)");
                            String option = scanner.next();
                            if (option.equals("Y")) {
                                System.out.println("please enter the song id which you want play");
                                int id = scanner.nextInt();
                                songService.playParticular(id);
                                System.out.println();
                            } else {
                                break;
                            }
                        } catch (SongNotFound e) {

                        }
                    }
                case 2:
                    displayFormat(displayAllSong);
                    System.out.println("Please enter song id which you want to play");
                    int choice2 = scanner.nextInt();
                    System.out.println("---------------------------------------------------------------------");
                    songService.playParticular(choice2);
                    System.out.println();
                    break;
                case 3:
                    System.out.println("-----------------Press 3 to create play list----------------------------");
                    System.out.println("Enter play list name::");
                    String playListName = scanner.next();
                    //scanner.nextLine();
                    playListRepository.addIntoDatabase(playListName);
                    System.out.println();
                    System.out.println("Enter play list name::");
                    break;
                case 4:
                    System.out.println("------------------insert song into play List------------------------------");
                    List<Song> displayAllSong1 = songRepository.displayAllSong();
                    displayFormat(displayAllSong1);
                    List<PlayList> getPlayListName1 = playListRepository.ShowPlayList();
                    for (PlayList playList : getPlayListName1) {
                        System.out.println(playList.getPlayListId() + " " + playList.getPlayListName());
                    }
                    System.out.println("Enter Playlist number for choose playList which you want add song::");
                    int playListId = scanner.nextInt();
                    displayFormat(displayAllSong);
                    System.out.println(playListRepository.ShowPlayList());
                    System.out.println("Enter Song id which song you want to add into playlist::");
                    int songId = scanner.nextInt();
                    playListRepository.insertSongIntoPlayList(playListId, songId);
                    break;
                case 5:
                    System.out.println("-------------------view to playlist--------------------------------------");
                    List<PlayList> getPlayListName = playListRepository.ShowPlayList();
                    for (PlayList playList : getPlayListName) {
                        System.out.println(playList.getPlayListId() + " " + playList.getPlayListName());
                    }
                    System.out.println("Press 1 for view song from the playlist");
                    System.out.println("Press 2 for Exit");

                    int choice3 = scanner.nextInt();
                    if (choice3 == 1) {
                        System.out.println("Please enter the playlist id which you view the song");
                        int playListIds = scanner.nextInt();
                        List<Song> getSongFromList = playListRepository.getSongFromList(playListIds, displayAllSong);
                        displayFormat(getSongFromList);
                    }
                    System.out.println("If you want to play song then press (Y/N)");
                    String option = scanner.next();
                    if (option.equals("Y")) {
                        System.out.println("please enter the song id which you want play");
                        int songIds = scanner.nextInt();
                        songService.playParticular(songIds);
                    } else {
                        break;
                    }
                case 6:

                    System.out.println("--------------------Sorted list---------------------------------------");
                    List<Song> songList = songRepository.sortSongs(displayAllSong);
                    displayFormat(songList);
                case 7:
                    System.out.println("Successful Exit");
                    System.out.println("--------------------------------------------------------------------------");


            }
        } while (task < 6);
    }

    private static void displayFormat(List<Song> songList) {
        System.out.println("|----------------------------------------------------------------------------------------------------------------------------------|");
        System.out.format("%-10s %-30s %-20s %-30s %-20s %-30s\n", "Id", "Name", "Duration", "AlbumName", "ArtistName", "Genre");
        for (Song song : songList) {
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------");
            System.out.format("%-10d %-30s %-20s %-30s %-20s %-30s\n", song.getId(), song.getName(), song.getDuration(), song.getAlbumName(), song.getArtistName(), song.getGenre());
        }
    }
}