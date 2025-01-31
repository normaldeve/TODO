package com.example.todo.controller;

import com.example.todo.domain.Todo;
import com.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    // TODO 생성
    @PostMapping
    public ResponseEntity<Map<String, Object>> createTodo(@RequestBody Todo todo) {
        Todo createdTodo = todoService.createTodo(
                todo.getTitle(),
                todo.getContent(),
                todo.getTday(),
                todo.getStartTime(),
                todo.getEndTime()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Todo created successfully");
        response.put("todo", createdTodo);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 특정 날짜의 Todo 조회 (GET)
    @GetMapping
    public ResponseEntity<Map<String, Object>> getTodosByDay(@RequestParam(name = "day") String day) {
        List<Todo> todos = todoService.readTodo(LocalDate.parse(day));

        Map<String, Object> response = new HashMap<>();
        response.put("message", todos.isEmpty() ? "No todos found for the given date" : "Todos retrieved successfully");
        response.put("todos", todos);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Todo 업데이트 (PUT)
    @PutMapping
    public ResponseEntity<Map<String, Object>> updateTodo(@RequestBody Todo todo) {
        Todo updatedTodo = todoService.updateTodo(
                todo.getTday(),
                todo.getTitle(),
                todo.getContent()
        );

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Todo updated successfully");
        response.put("todo", updatedTodo);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // Todo 삭제 (DELETE)
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteTodo(@RequestParam(name = "day") String day) {
        todoService.deleteTodo(LocalDate.parse(day));

        Map<String, String> response = new HashMap<>();
        response.put("message", "Todo deleted successfully");

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
