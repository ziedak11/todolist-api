package tn.ims.todolist.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.ims.todolist.model.UserAccount;
import tn.ims.todolist.repository.UserRepository;
import tn.ims.todolist.service.UserService;

@Service
public class DefaultUserService implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserAccount createUser(UserAccount user) {
		return userRepository.save(user);
	}

	@Override
	public UserAccount findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
