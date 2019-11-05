package tn.ims.todolist.service;


import tn.ims.todolist.model.UserAccount;

public interface UserService {
	
	UserAccount createUser(UserAccount user);

	UserAccount findUserByEmail(String email);
}
