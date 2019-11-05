package tn.ims.todolist.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tn.ims.todolist.model.Task;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

	@Query("select t from Task t where t.userAccount.email  = :email")
	public List<Task> findTasksByEmail(String email);

	@Query("select t from Task t where t.id = :taskId and t.userAccount.email  = :email")
	public Task findTaskByTaskIdAndEmail(Long taskId, String email);

    @Modifying
    @Transactional
	public void deleteByIdAndUserAccountEmail(Long taskId, String email);
}

