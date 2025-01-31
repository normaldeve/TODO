package com.example.todo.controller;

import com.example.todo.domain.Todo;
import com.example.todo.service.TodoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // TODO 생성
    @PostMapping
    public void createTodo(
            @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
            @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content
    ) {
        todoService.createTodo(title, content, day, startTime, endTime);
    }

    // 특정 날짜의 Todo 조회 (GET)
    @GetMapping
    public List<Todo> getTodosByDay(
            @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day
    ) {
        return todoService.readTodo(day);
    }

    // 특정 날짜와 시간 범위에 해당하는 Todo 조회 (GET)
    @GetMapping("/range")
    public List<Todo> getTodosByTimeRange(
            @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
            @RequestParam(name = "startTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime startTime,
            @RequestParam(name = "endTime") @DateTimeFormat(iso = DateTimeFormat.ISO.TIME) LocalTime endTime
    ) {
        return todoService.readTodos(day, startTime, endTime);
    }

    // Todo 업데이트 (PUT)
    @PutMapping
    public void updateTodo(
            @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "content") String content
    ) {
        todoService.updateTodo(day, title, content);
    }

    // Todo 삭제 (DELETE)
    @DeleteMapping
    public void deleteTodo(
            @RequestParam(name = "day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day
    ) {
        todoService.deleteTodo(day);
    }
}
