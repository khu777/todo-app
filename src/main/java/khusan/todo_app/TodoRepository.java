package khusan.todo_app;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends JpaRepository<TodoItem, Integer> {

    // БЫЛО: findAllByOrderByIsDoneAscAndIdDesc
    // СТАЛО (убрали And):
    List<TodoItem> findAllByOrderByIsDoneAscIdDesc();

}