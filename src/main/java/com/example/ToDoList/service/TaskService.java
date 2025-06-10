package com.example.ToDoList.service;

import com.example.ToDoList.dto.TaskRequestDto;
import com.example.ToDoList.dto.TaskResponseDto;
import com.example.ToDoList.model.Task;
import com.example.ToDoList.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public TaskResponseDto addTask(TaskRequestDto requestDto) {
        Task task = new Task();
        task.setTitle(requestDto.getTitle());
        task.setDescription(requestDto.getDescription());
        task.setCompleted(requestDto.getCompleted() != null && requestDto.getCompleted());
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);

    }

    public TaskResponseDto getTaskById(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        return convertToDto(task);
    }

    public List<TaskResponseDto> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public TaskResponseDto updateTask(Long id, TaskRequestDto requestDto) {
            Task task = taskRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Task not found"));
            if(requestDto.getTitle() != null) {
                task.setTitle(requestDto.getTitle());
            }
            if(requestDto.getDescription() != null) {
                task.setDescription(requestDto.getDescription());
            }
            if(requestDto.getCompleted() != null) {
                task.setCompleted(requestDto.getCompleted());
            }
            Task updatedTask = taskRepository.save(task);
            return convertToDto(updatedTask);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private TaskResponseDto convertToDto(Task task) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setCompleted(task.isCompleted());
        return dto;
    }




}
