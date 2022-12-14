package com.steven.todoapp.service.DTO;

import com.steven.todoapp.persistence.entity.TaskStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskInDTO {
    private String title;
    private String description;;
    private LocalDateTime eta;
}
