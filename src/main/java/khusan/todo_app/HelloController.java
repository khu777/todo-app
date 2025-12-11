package khusan.todo_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {

    private List<TodoItem> tasks = new ArrayList<>();

    public HelloController() {
        tasks.add(new TodoItem(1, "Learn Spring Boot", true));
        tasks.add(new TodoItem(2, "Build my own project", false));
        tasks.add(new TodoItem(3, "Go for a walk", false));
    }

    @GetMapping("/")
    public String openTodoPage(Model model) {
        model.addAttribute("myTasks", tasks);
        return "todo";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String title) {
        int newId = tasks.size() + 1;
        TodoItem newItem = new TodoItem(newId, title, false);
        tasks.add(newItem);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable int id) {
        tasks.removeIf(t -> t.id() == id);
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTask(@PathVariable int id) {
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).id() == id) {
                TodoItem item = tasks.get(i);
                tasks.set(i, new TodoItem(item.id(), item.title(), !item.isDone()));
                break;
            }
        }
        return "redirect:/";
    }
}