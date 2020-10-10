package com.galendata.eventtracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.galendata.eventtracker.model.Events;

@Repository
public interface EventRepository extends JpaRepository<Events, Long> {

}
