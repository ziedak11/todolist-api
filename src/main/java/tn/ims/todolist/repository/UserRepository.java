package tn.ims.todolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.ims.todolist.model.UserAccount;

@Repository
public interface UserRepository extends CrudRepository<UserAccount, Long> {

	public UserAccount findByEmail(String email);
}
