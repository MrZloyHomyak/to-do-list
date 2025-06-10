package com.example.ToDoList.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class TaskResponseDto {
    private Long id;
    private String title;
    private String description;
    private boolean completed;
}
