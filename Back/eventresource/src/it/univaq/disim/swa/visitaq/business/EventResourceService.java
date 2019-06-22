package it.univaq.disim.swa.visitaq.business;

import java.util.List;

import it.univaq.disim.swa.visitaq.domain.Event;

public interface EventResourceService {

	Event insertEvent(Event attraction) throws EventResourceBusinessException;

	void deleteEvent(Long id, Event attraction) throws EventResourceBusinessException;

	Event updateEvent(Event attraction, Long id) throws EventResourceBusinessException;
	
	List<Event> selectEvents() throws EventResourceBusinessException;
	
	Event selectEventDetail(Long id) throws EventResourceBusinessException;
}