package tn.ims.todolist.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.ims.todolist.model.UserAccount;
import tn.ims.todolist.service.UserService;

@CrossOrigin
@RestController
public class SignUpController {

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping(path = "/signup")
	public ResponseEntity<?> signUp(@Valid @RequestBody UserAccount userAccount) {
		userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
		userService.createUser(userAccount);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

}
