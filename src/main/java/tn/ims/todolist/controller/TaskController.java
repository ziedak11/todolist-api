package tn.ims.todolist.controller;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;
import tn.ims.todolist.model.Task;
import tn.ims.todolist.model.UserAccount;
import tn.ims.todolist.service.TaskService;
import tn.ims.todolist.service.UserService;

@CrossOrigin
@Slf4j
@RestController
@PreAuthorize("isAuthenticated()")
public class TaskController {

	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@GetMapping(value = "/tasks/{taskId}")
	public ResponseEntity<?> getTask(@PathVariable Long taskId,Principal principal) {
		Task task = taskService.findTaskByTaskIdAndUserEmail(taskId,principal.getName());
		return new ResponseEntity<>(task, HttpStatus.OK);
	}
	@GetMapping("/tasks")
	public ResponseEntity<List<Task>> findAllTasks(Principal principal) {
		List<Task> tasks = taskService.findAllTasksByEmail(principal.getName());
		return new ResponseEntity<List<Task>>(tasks, HttpStatus.OK);
	}
	@PostMapping("/tasks")
	public ResponseEntity<?> createTask(@Valid @RequestBody Task task,Principal principal) {
		UserAccount user = userService.findUserByEmail(principal.getName());
		task.setUserAccount(user);
		taskService.createTask(task);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	@PutMapping("/tasks/{taskId}")
	public ResponseEntity<?> updateTask(@Valid @RequestBody Task task, @PathVariable Long taskId,Principal principal) {
		task.setId(taskId);
		taskService.updateTask(task,principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
	@DeleteMapping(value = "/tasks/{taskId}")
	public ResponseEntity<?> deleteTask(@PathVariable Long taskId,Principal principal) {
		taskService.deleteTaskByTaskIdAndUserEmail(taskId,principal.getName());
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
