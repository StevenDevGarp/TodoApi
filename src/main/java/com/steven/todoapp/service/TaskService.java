package com.steven.todoapp.service;

import com.steven.todoapp.exceptions.ToDoExceptions;
import com.steven.todoapp.mapper.TaskInDTOToTask;
import com.steven.todoapp.persistence.entity.Task;
import com.steven.todoapp.persistence.entity.TaskStatus;
import com.steven.todoapp.persistence.repositoy.TaskRespository;
import com.steven.todoapp.service.DTO.TaskInDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {


    private final TaskRespository repository;
    private final TaskInDTOToTask mapper;


    public TaskService(TaskRespository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createTask (TaskInDTO TaskInDTO) {
        Task task = mapper.map(TaskInDTO);
        return this.repository.save(task);
    }


    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAlByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }
    @Transactional
    public void UpdateTaskFinished (Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if (optionalTask.isEmpty()){
            throw new ToDoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
