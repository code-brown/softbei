package org.spring.trainer.mapper;

import org.apache.ibatis.annotations.*;
import org.spring.trainer.entity.TrainingTask;

import java.util.List;

@Mapper
public interface TrainingTaskMapper {

    @Select("SELECT * FROM TRAINER.TRAINERTASKS")
    List<TrainingTask> findAll();

    @Select("SELECT * FROM TRAINER.TRAINERTASKS WHERE id = #{id}")
    TrainingTask findById(Integer id);

    @Insert("INSERT INTO TRAINER.TRAINERTASKS(title, description, type, start_time, end_time) VALUES(#{title}, #{description}, #{type}, #{startTime}, #{endTime})")
    void insert(TrainingTask trainingTask);

    @Update("UPDATE TRAINER.TRAINERTASKS SET title = #{title}, description = #{description}, type = #{type}, start_time = #{startTime}, end_time = #{endTime} WHERE id = #{id}")
    void update(TrainingTask trainingTask);

    @Delete("DELETE FROM TRAINER.TRAINERTASKS WHERE id = #{id}")
    void deleteById(Integer id);
}
