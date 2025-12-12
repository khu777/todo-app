package khusan.todo_app;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// Мы наследуемся от JpaRepository.
// <TodoItem, Integer> значит: "Я работаю с таблицей TodoItem, у которой ID — это Integer"
@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {
    // Тут пусто, потому что Спринг УЖЕ написал за нас методы:
    // save(), findAll(), deleteById() и другие.
}