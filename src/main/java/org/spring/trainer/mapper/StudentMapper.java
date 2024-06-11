package org.spring.trainer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.spring.trainer.entity.Student;

import java.util.List;


@Mapper
    public interface StudentMapper {
        List<Student> getAllStudents();
    }

