package com.example.todo.service;

import com.example.todo.TodoApplication;
import com.example.todo.domain.Todo;
import com.example.todo.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@Transactional
public class TodoService {
    private final ToDoRepository toDoRepository;

    private static final Logger logger = LoggerFactory.getLogger(TodoApplication.class);

    public TodoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Transactional
    public void createTodo(String title, String content, LocalDate day, LocalTime startTime, LocalTime endTime) {
        logger.info("started to create TODO");
        Todo todo = new Todo();
        todo.setTitle(title);
        todo.setContent(content);
        todo.setDay(day);
        todo.setStartTime(startTime);
        todo.setEndTime(endTime);
        todo.setCreatedDate(LocalDateTime.now());
        todo.setUpdatedDate(LocalDateTime.now()); // 일단 생성 시간과 동일하게 설정하고 업데이트 할때 수정할 계획
        todo.setDone(false);
        toDoRepository.save(todo);
        logger.info("end to create TODO");
    }

    @Transactional
    public List<Todo> readTodo(LocalDate day) { // 특정 날을 입력하면 해당 날에 해당하는 TODO 출력
        return toDoRepository.findAllByDay(day);
    }

    public List<Todo> readTodos(LocalDate day, LocalTime start, LocalTime end) {
        return toDoRepository.findAllByDayAndStartTimeBetween(day, start, end);
    }

    public void updateTodo(LocalDate day, String title, String content) {
        Todo todo = toDoRepository.getFirstByDay(day);
        todo.setTitle(title);
        todo.setContent(content);
        toDoRepository.save(todo);
        logger.info("update todo");
    }

    public void deleteTodo(LocalDate day) {
        toDoRepository.deleteAllByDay(day);
    }
}
