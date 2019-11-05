package tn.ims.todolist.service.impl;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tn.ims.todolist.model.UserAccount;
import tn.ims.todolist.repository.UserRepository;

@Service
public class DefaultUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository UserRepository;

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		UserAccount userAccount = UserRepository.findByEmail(userName);
		if (userAccount != null)
			return new User(userAccount.getEmail(), userAccount.getPassword(),
					Collections.emptyList());
		else
			throw new UsernameNotFoundException("User not found by name: " + userName);
	}
}
