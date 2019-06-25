package it.univaq.disim.swa.visitaq.business;

import java.util.List;

import it.univaq.disim.swa.visitaq.domain.Session;
import it.univaq.disim.swa.visitaq.domain.User;

public interface AccountResourceService {
	 
	Boolean insertUser(User user) throws VisitaqBusinessException;

	Boolean deleteUser(Long id) throws VisitaqBusinessException;

	User updateUser(User user, Long id) throws VisitaqBusinessException;

	Session loginUser(User user) throws VisitaqBusinessException;

	Boolean logoutUser(String token) throws VisitaqBusinessException;

	Boolean checkSession(String token) throws VisitaqBusinessException;

	List<User> selectAllUsers() throws VisitaqBusinessException;

	User selectUserByEmail(String email) throws VisitaqBusinessException;
}