package com.kodnest.tunehub.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Playlist;
import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.PlaylistService;
import com.kodnest.tunehub.service.SongService;
import com.kodnest.tunehub.serviceimpl.PlaylistServiceImpl;

@Controller
public class PlaylistController {
	@Autowired
	SongService sserv;
	@Autowired
	PlaylistService pserv;
	
	@GetMapping("/map-createPlayList")
	public String createPlaylist(Model model) {
		List<Song> songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist",songslist );
			return "createplayList";
	}
	
	@PostMapping("add-playlist")
	public String addPlaylist(@ModelAttribute Playlist playlist) {
		pserv.addplaylist(playlist);
		List<Song> songslist=playlist.getSongs();
		System.out.println(playlist);
		for(Song song: songslist) {
			song.getPlaylists().add(playlist);
			sserv.updateSong(song);
		}
		
		return "playlistsuccess";
	}
	
	
	@GetMapping("map-viewPlayList")
	public String viewPlaylist(Model model) {
		List<Playlist> playlist=pserv.fetchAllSongs();
		model.addAttribute("playlist",playlist );
		return "viewPlaylist";
	
	}
	
	
	

}
	
	
	
	
	
/*@GetMapping("/createplaylists")
	public String createPlaylists(Model model)
	{
		List<Playlist> songs=playlistServiceImpl.fetchAllSongs();
		model.addAttribute("songs",songs);
		
		return "createplaylists";
	}
	@PostMapping("/addplaylist")
	public String addplaylist(@ModelAttribute Playlist playlist)
	{
		//updating the playlist table
		playlistServiceImpl.addplaylist(playlist);
		
		//updating the song table
		List<Song> songs=playlist.getSongs();
		for(Song s : songs) {
			s.getPlaylists().add(playlist);
		   sserv.updateSong(s);
		}

		return "adminhome";
	}
	@PostMapping("/viewplaylists")
	public String viewplaylists(Model model) {
		
		List<Playlist> songs	 = playlistServiceImpl.fetchAllSongs();
		model.addAttribute("songs",songs);
		
		return "displaylists";
	}
}*/