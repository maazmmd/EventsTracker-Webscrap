package com.galendata.eventtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.galendata.eventtracker.model.Events;
import com.galendata.eventtracker.repository.EventRepository;

@Service
public class EventServiceImpl extends WebscrapRestAction implements EventService {

	@Autowired
	EventRepository eventRepository;

	@Override
	public List<Events> findAll() {
		return eventRepository.findAll();
	}

	@Override
	public Events save(Events expense) {
		eventRepository.save(expense);
		return expense;
	}

	@Override
	public Events findById(Long id) {
		if (eventRepository.findById(id).isPresent()) {
			return eventRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public void delete(Long id) {
		Events expense = findById(id);
		eventRepository.delete(expense);
	}

	@Override
	public void saveAll() {
		String[] urls = getUrls();
		List<Events> events1 = parseWebPage1(urls[0]);
		eventRepository.saveAll(events1);

		List<Events> events2 = parseWebPage2(urls[1]);
		eventRepository.saveAll(events2);
	}
}