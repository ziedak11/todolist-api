package tn.ims.todolist.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.ims.todolist.exception.ResourceNotFoundException;
import tn.ims.todolist.model.Task;
import tn.ims.todolist.repository.TaskRepository;
import tn.ims.todolist.service.TaskService;

@Service
public class DefaultTaskService implements TaskService {

	@Autowired
	private TaskRepository taskRepository;

	@Override
	public Task createTask(Task task) {
		return taskRepository.save(task);
	}

	@Override
	public void updateTask(Task task, String email) {
		Task savedTask = this.findTaskByTaskIdAndUserEmail(task.getId(), email);
		savedTask.setDescription(task.getDescription());
		savedTask.setTitle(task.getTitle());
		savedTask.setDueDate(task.getDueDate());
		savedTask.setPriority(task.getPriority());
		taskRepository.save(savedTask);
	}

	@Override
	public Task findTaskByTaskIdAndUserEmail(Long taskId, String email) {
		Optional<Task> optional = taskRepository.findById(taskId);
		if (optional.isPresent()) {
			return optional.get();
		}
		throw new ResourceNotFoundException("Task not found");
	}

	@Override
	public void deleteTaskByTaskIdAndUserEmail(Long taskId, String email) {
		taskRepository.deleteByIdAndUserAccountEmail(taskId, email);
	}

	@Override
	public List<Task> findAllTasksByEmail(String email) {
		return taskRepository.findTasksByEmail(email);
	}
}
