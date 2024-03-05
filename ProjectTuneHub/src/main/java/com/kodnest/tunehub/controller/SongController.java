package com.kodnest.tunehub.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.kodnest.tunehub.entity.Song;
import com.kodnest.tunehub.service.SongService;

@Controller
public class SongController {
	
	@Autowired
	SongService songService;
	
	@PostMapping("/addsong")
	public String addsong(@ModelAttribute Song song){
		
		boolean songStatus=songService.songExists(song.getName());
		if(songStatus==false){
			songService.addSong(song);
			System.out.println("Song added successfully");
		}
		else {
			System.out.println("Song already exists");
		}
		return "adminhome";
 }
@GetMapping("viewSongs")
public String fetchAllSongs(Model model)
{
	List<Song> songs=songService.fetchAllSongs();
	model.addAttribute("songs",songs);
	
	return "viewsongs";
}
@GetMapping("playSongs")
public String playSongs(Model model)
{
	boolean premium=true;
	if(premium) {
		
	List<Song> songs=songService.fetchAllSongs();
	model.addAttribute("songs",songs);
	 return "viewsongs";
	}
	else {
		return "subscriptionform";
	}
  }
}
