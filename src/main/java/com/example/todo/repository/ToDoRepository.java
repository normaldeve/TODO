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

    Todo getFirstByDay(LocalDate day);

    List<Todo> findAllByDay(LocalDate day);

    // 특정 날짜에서 주어진 시간 범위 내에 시작하는 일정 조회
    List<Todo> findAllByDayAndStartTimeBetween(LocalDate day, LocalTime start, LocalTime end);

    void deleteAllByDay(LocalDate day);
}
