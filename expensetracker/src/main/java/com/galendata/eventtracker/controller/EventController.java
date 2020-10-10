package com.galendata.eventtracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.galendata.eventtracker.model.Events;
import com.galendata.eventtracker.service.EventService;


@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
public class EventController {

	@Autowired
	EventService eventService;

	@GetMapping("/events")
	public ResponseEntity<List<Events>> get() {
		List<Events> events = eventService.findAll();
		return new ResponseEntity<List<Events>>(events, HttpStatus.OK);
	}

	@PostMapping("/events")
	public ResponseEntity<Events> save(@RequestBody Events expense) {
		Events eventOne = eventService.save(expense);
		return new ResponseEntity<Events>(eventOne, HttpStatus.OK);
	}

	@GetMapping("/events/{id}")
	public ResponseEntity<Events> get(@PathVariable("id") Long id) {
		Events event = eventService.findById(id);
		return new ResponseEntity<Events>(event, HttpStatus.OK);
	}

	@DeleteMapping("/events/{id}")
	public ResponseEntity<String> delete(@PathVariable("id") Long id) {
		eventService.delete(id);
		return new ResponseEntity<String>("Event is deleted successfully.!", HttpStatus.OK);
	}
	
	@PostMapping("/webscrap")
	public ResponseEntity<String> webScrappingFromWebPages() {
		eventService.saveAll();
		return new ResponseEntity<String>("{\"success\": \"true\"}", HttpStatus.OK);
	}

}
