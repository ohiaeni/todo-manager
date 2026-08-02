package com.todomanager.infrastructure;

import com.todomanager.domain.Task;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TaskMapper {
    List<Task> findByUserId(@Param("userId") Long userId);

    Long nextId();

    int insert(@Param("id") Long id, @Param("userId") Long userId, @Param("title") String title,
            @Param("completed") boolean completed);

    boolean existsById(@Param("taskId") Long taskId);

    Task findByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);

    int updateByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId,
            @Param("title") String title, @Param("completed") boolean completed);

    int deleteByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);
}
