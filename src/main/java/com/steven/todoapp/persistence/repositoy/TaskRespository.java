package com.steven.todoapp.persistence.repositoy;

import com.steven.todoapp.persistence.entity.Task;
import com.steven.todoapp.persistence.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRespository extends JpaRepository<Task, Long>{

    public List<Task> findAllByTaskStatus(TaskStatus status);

    @Modifying
    @Query(value = "UPDATE TASK SET FINISHED = TRUE WHERE ID =:id", nativeQuery = true)
    public void markTaskAsFinished(@Param("id") Long id);

}
