package com.todomanager.infrastructure;

import com.todomanager.domain.Task;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TaskMapper {
    List<Task> findAll();
}
