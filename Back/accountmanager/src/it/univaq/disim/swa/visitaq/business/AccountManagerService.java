package it.univaq.disim.swa.visitaq.business;

import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

public interface AccountManagerService {
	 
	Boolean insertUser(User user) throws AccountManagerBusinessException;

	void deleteUser(Long id) throws AccountManagerBusinessException;

	User updateUser(User user, Long id) throws AccountManagerBusinessException;

	Session loginUser(User user) throws AccountManagerBusinessException;

	void logoutUser(String token) throws AccountManagerBusinessException;
}