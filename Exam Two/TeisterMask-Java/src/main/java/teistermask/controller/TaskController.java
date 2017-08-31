package teistermask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import teistermask.bindingModel.TaskBindingModel;
import teistermask.entity.Task;
import teistermask.repository.TaskRepository;

import java.util.List;

@Controller
public class TaskController {
	private final TaskRepository taskRepository;

	@Autowired
	public TaskController(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@GetMapping("/")
	public String index(Model model) {
		List<Task> openTasks = taskRepository.findAllByStatus("Open");
		List<Task> inProgressTasks = taskRepository.findAllByStatus("In Progress");
		List<Task> finishedTasks = taskRepository.findAllByStatus("Finished");

		model.addAttribute("openTasks", openTasks);
		model.addAttribute("finishedTasks", finishedTasks);
		model.addAttribute("inProgressTasks", inProgressTasks);

		model.addAttribute("view", "task/index");
		return "base-layout";
	}

	@GetMapping("/create")
	public String create(Model model) {

		model.addAttribute("view", "task/create");
		return "base-layout";
	}

	@PostMapping("/create")
	public String createProcess(Model model, TaskBindingModel taskBindingModel) {

		// redirects to main page if the viewmodel is null
		if (taskBindingModel == null){
			return "redirect:/";
		}

		// redirects to main page if the title or the comments are not entered and submitted
		if (taskBindingModel.getTitle().equals("") || taskBindingModel.getStatus().equals("")){
			return "redirect:/";
		}

		//creates a new task with the info from the taskbindingmodel and saves it to the database
		Task task = new Task(taskBindingModel.getStatus(), taskBindingModel.getTitle());
		taskRepository.saveAndFlush(task);

		//redirects to main page
		return "redirect:/";
	}

	@GetMapping("/edit/{id}")
	public String edit(Model model, @PathVariable int id) {
		Task task = taskRepository.findOne(id);
		if (task != null){
			model.addAttribute("task", task);
			model.addAttribute("view", "task/edit");
			return "base-layout";
		}

		return "redirect:/";
	}

	@PostMapping("/edit/{id}")
	public String editProcess(@PathVariable int id, TaskBindingModel taskBindingModel, Model model) {

		//this serves not to delete the entered info, if one of the fields is empty (pointless here)
		if (taskBindingModel.getTitle().equals("") || taskBindingModel.getStatus().equals("")){

			Task newTaskForForm = new Task();
			newTaskForForm.setTitle(taskBindingModel.getTitle());
			newTaskForForm.setStatus(taskBindingModel.getStatus());
			newTaskForForm.setId(id);

			model.addAttribute("task", newTaskForForm);
			model.addAttribute("view", "task/edit");

			return "base-layout";
		}

		Task task = taskRepository.findOne(id);

		if (task != null) {
			task.setId(id);
			task.setStatus(taskBindingModel.getStatus());
			task.setTitle(taskBindingModel.getTitle());
			taskRepository.saveAndFlush(task);
		}

		return "redirect:/";
	}
}