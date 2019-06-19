package it.univaq.disim.swa.visitaq.business;

import java.util.List;
import java.util.Map;

import it.univaq.disim.swa.visitaq.domain.Attraction;

public interface AttractionManagerService {

	Attraction insertAttraction(Attraction attraction) throws AttractionManagerBusinessException;

	void deleteAttraction(Long id, Attraction attraction) throws AttractionManagerBusinessException;

	Attraction updateAttraction(Attraction attraction, Long id) throws AttractionManagerBusinessException;
	
	 Attraction selectAttractionDetail(Long id) throws AttractionManagerBusinessException;
	
	List<Attraction> selectAttractions() throws AttractionManagerBusinessException;
}