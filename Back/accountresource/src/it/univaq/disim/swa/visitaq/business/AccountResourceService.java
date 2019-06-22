package it.univaq.disim.swa.visitaq.business;

import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

public interface AccountResourceService {
	 
	Boolean insertUser(User user) throws AccountResourceBusinessException;

	void deleteUser(Long id) throws AccountResourceBusinessException;

	User updateUser(User user, Long id) throws AccountResourceBusinessException;

	Session loginUser(User user) throws AccountResourceBusinessException;

	void logoutUser(String token) throws AccountResourceBusinessException;
}