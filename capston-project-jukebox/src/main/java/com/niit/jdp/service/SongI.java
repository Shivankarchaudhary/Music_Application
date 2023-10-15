package com.niit.jdp.service;

import com.niit.jdp.model.Song;

import java.util.List;

public interface SongI {
    //  abstraction method
    public List<Song> getSongSearchBySongName(List<Song> songList, String songName) throws SongNotFound;
}
