package com.example.todo.controller;

import com.example.todo.domain.Todo;
import com.example.todo.service.TodoService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(
                todo.getTitle(),
                todo.getContent(),
                todo.getTday(),
                todo.getStartTime(),
                todo.getEndTime()
        );
    }

    // 특정 날짜의 Todo 조회 (GET)
    @GetMapping
    public List<Todo> getTodosByDay(
            @RequestParam(name = "day") String day // day는 날짜 포맷으로 String 받기
    ) {
        return todoService.readTodo(LocalDate.parse(day));
    }

    // Todo 업데이트 (PUT)
    @PutMapping
    public Todo updateTodo(
            @RequestBody Todo todo // @RequestBody로 Todo 객체를 받아서 업데이트
    ) {
        return todoService.updateTodo(
                todo.getTday(),
                todo.getTitle(),
                todo.getContent()
        );
    }

    // Todo 삭제 (DELETE)
    @DeleteMapping
    public void deleteTodo(@RequestParam(name = "day") String day) {
        todoService.deleteTodo(LocalDate.parse(day));
    }
}
