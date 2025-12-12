package khusan.todo_app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class HelloController {

    // Раньше тут был List<TodoItem>. Теперь тут Репозиторий (связь с базой).
    private final TodoRepository repository;

    // Спринг сам найдет репозиторий и даст его нам (Dependency Injection)
    public HelloController(TodoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String openTodoPage(Model model) {
        // Раньше: return tasks;
        // Теперь: Иди в базу и принеси всё, что там есть (findAll)
        List<TodoItem> tasks = repository.findAll();
        model.addAttribute("myTasks", tasks);
        return "todo";
    }

    @PostMapping("/add")
    public String addTask(@RequestParam String title) {
        // Мы больше не придумываем ID сами. База сделает это за нас.
        // Пишем null вместо ID.
        TodoItem newItem = new TodoItem(null, title, false);

        // Сохраняем в базу навсегда!
        repository.save(newItem);

        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable Integer id) {
        // Удаляем из базы по ID
        repository.deleteById(id);
        return "redirect:/";
    }

    @PostMapping("/toggle/{id}")
    public String toggleTask(@PathVariable Integer id) {
        // 1. Ищем задачу в базе по ID
        TodoItem item = repository.findById(id).orElse(null);

        if (item != null) {
            // 2. Меняем статус на противоположный
            item.setDone(!item.isDone());
            // 3. Сохраняем (Спринг поймет, что это обновление, а не создание новой)
            repository.save(item);
        }
        return "redirect:/";
    }
}