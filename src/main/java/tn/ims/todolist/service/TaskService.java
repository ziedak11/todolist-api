package tn.ims.todolist.service;

import java.util.List;

import tn.ims.todolist.model.Task;

public interface TaskService {

	Task createTask(Task task);

	void updateTask(Task task, String email);

	Task findTaskByTaskIdAndUserEmail(Long taskId,String  email);

	List<Task> findAllTasksByEmail(String  email);

	void deleteTaskByTaskIdAndUserEmail(Long taskId,String  email);
}
