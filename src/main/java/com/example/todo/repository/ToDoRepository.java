package com.example.todo.repository;

import com.example.todo.domain.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface ToDoRepository extends JpaRepository<Todo, Integer> {
    List<Todo> findAllByDone(boolean done);

    Todo getFirstByTday(LocalDate day);

    List<Todo> findAllByTday(LocalDate day);

    void deleteAllByTday(LocalDate day);
}
