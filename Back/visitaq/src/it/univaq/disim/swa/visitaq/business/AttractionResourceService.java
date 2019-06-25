package it.univaq.disim.swa.visitaq.business;

import java.util.List;

import it.univaq.disim.swa.visitaq.domain.Attraction;

public interface AttractionResourceService {

	Attraction insertAttraction(Attraction attraction) throws VisitaqBusinessException;

	void deleteAttraction(Long id, Attraction attraction) throws VisitaqBusinessException;

	Attraction updateAttraction(Attraction attraction, Long id) throws VisitaqBusinessException;
	
	 Attraction selectAttractionDetail(Long id) throws VisitaqBusinessException;
	
	List<Attraction> selectAttractions() throws VisitaqBusinessException;

	List<Attraction> selectAttractionsByUser(Long id) throws VisitaqBusinessException;

	List<Attraction> selectAttractionsByCategory(Long id) throws VisitaqBusinessException;
}