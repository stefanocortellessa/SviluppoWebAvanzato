package it.univaq.disim.swa.visitaq.business;

import java.util.List;

import it.univaq.disim.swa.visitaq.domain.Attraction;

public interface AttractionResourceService {

	Attraction insertAttraction(Attraction attraction) throws AttractionResourceBusinessException;

	void deleteAttraction(Long id, Attraction attraction) throws AttractionResourceBusinessException;

	Attraction updateAttraction(Attraction attraction, Long id) throws AttractionResourceBusinessException;
	
	 Attraction selectAttractionDetail(Long id) throws AttractionResourceBusinessException;
	
	List<Attraction> selectAttractions() throws AttractionResourceBusinessException;
}