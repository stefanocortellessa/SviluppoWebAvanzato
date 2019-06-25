package it.univaq.disim.swa.visitaq.business;

import java.util.List;

import it.univaq.disim.swa.visitaq.domain.Event;

public interface EventResourceService {

	Event insertEvent(Event attraction) throws VisitaqBusinessException;

	void deleteEvent(Long id, Event attraction) throws VisitaqBusinessException;

	Event updateEvent(Event attraction, Long id) throws VisitaqBusinessException;
	
	List<Event> selectEvents() throws VisitaqBusinessException;
	
	Event selectEventDetail(Long id) throws VisitaqBusinessException;

	List<Event> selectEventsByUser(Long id) throws VisitaqBusinessException;

	List<Event> selectEventsByCategory(Long id) throws VisitaqBusinessException;
}