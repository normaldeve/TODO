package com.example.todo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Todo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String content;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate tday; // 년월일
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime startTime; // 일정 시작 시간
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime endTime; // 일정 끝나는 시간
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private boolean done;
}
