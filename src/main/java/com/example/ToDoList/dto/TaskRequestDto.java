package com.example.ToDoList.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;

@Getter
public class TaskRequestDto {
    @NotBlank(message = "Title cannot be blank")
    private String title;
    private String description;
    private Boolean completed;
}
