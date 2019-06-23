package it.univaq.disim.swa.visitaq.business;

import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

public interface AccountResourceService {
	 
	Boolean insertUser(User user) throws VisitaqBusinessException;

	void deleteUser(Long id) throws VisitaqBusinessException;

	User updateUser(User user, Long id) throws VisitaqBusinessException;

	Session loginUser(User user) throws VisitaqBusinessException;

	void logoutUser(String token) throws VisitaqBusinessException;

	Boolean checkSession(String token) throws VisitaqBusinessException;
}