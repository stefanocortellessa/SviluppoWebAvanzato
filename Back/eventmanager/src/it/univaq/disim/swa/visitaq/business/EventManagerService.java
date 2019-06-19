package it.univaq.disim.swa.visitaq.business;

import java.util.List;

import it.univaq.disim.swa.visitaq.domain.Event;

public interface EventManagerService {

	Event insertEvent(Event attraction) throws EventManagerBusinessException;

	void deleteEvent(Long id, Event attraction) throws EventManagerBusinessException;

	Event updateEvent(Event attraction, Long id) throws EventManagerBusinessException;
	
	List<Event> selectEvents() throws EventManagerBusinessException;
	
	Event selectEventDetail(Long id) throws EventManagerBusinessException;
}