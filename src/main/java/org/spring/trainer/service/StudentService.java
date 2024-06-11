package org.spring.trainer.service;


import org.spring.trainer.entity.Student;
import org.spring.trainer.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudents() {
        return studentMapper.getAllStudents();
    }


}
