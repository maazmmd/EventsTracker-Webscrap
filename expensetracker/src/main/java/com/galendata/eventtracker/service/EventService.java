package com.galendata.eventtracker.service;

import java.util.List;

import com.galendata.eventtracker.model.Events;

public interface EventService {
	
	List<Events> findAll();
	
	Events save(Events event);
	
	Events findById(Long id);
	
	void delete(Long id);

	void saveAll();
}
