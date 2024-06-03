package org.spring.trainer.mapper;

import org.apache.ibatis.annotations.*;
import org.spring.trainer.entity.TrainingTask;

import java.util.List;

@Mapper
public interface TrainingTaskMapper {

    @Select("SELECT * FROM TRAINERTASKS")
    List<TrainingTask> findAll();

    @Select("SELECT * FROM TRAINERTASKS WHERE id = #{id}")
    TrainingTask findById(Integer id);

    @Insert("INSERT INTO TRAINERTASKS(title, description, type, start_time, end_time, subjects) VALUES(#{title}, #{description}, #{type}, #{startTime}, #{endTime}, #{subjects})")
    void insert(TrainingTask trainingTask);

    @Update("UPDATE TRAINERTASKS SET title = #{title}, description = #{description}, type = #{type}, start_time = #{startTime}, end_time = #{endTime}, subjects = #{subjects} WHERE id = #{id}")
    void update(TrainingTask trainingTask);

    @Delete("DELETE FROM TRAINERTASKS WHERE id = #{id}")
    void deleteById(Integer id);
}
