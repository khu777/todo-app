package khusan.todo_app;

import jakarta.persistence.*;

@Entity
@Table(name = "todos")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String title;

    private boolean isDone;

    // --- 1. Пустой конструктор (Обязателен для базы данных) ---
    public TodoItem() {
    }

    // --- 2. Конструктор для создания задач (тот, которого не хватало) ---
    public TodoItem(Integer id, String title, boolean isDone) {
        this.id = id;
        this.title = title;
        this.isDone = isDone;
    }

    // --- 3. Геттеры и Сеттеры (чтобы получать и менять данные) ---
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}