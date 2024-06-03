package org.spring.trainer.service;

import org.spring.trainer.entity.TrainingTask;
import org.spring.trainer.mapper.TrainingTaskMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingTaskService {

    private final TrainingTaskMapper trainingTaskMapper;

    @Autowired
    public TrainingTaskService(TrainingTaskMapper trainingTaskMapper) {
        this.trainingTaskMapper = trainingTaskMapper;
    }

    public List<TrainingTask> findAllTrainingTasks() {
        return trainingTaskMapper.findAll();
    }

    public TrainingTask findTrainingTaskById(Integer id) {
        return trainingTaskMapper.findById(id);
    }

    public void addTrainingTask(TrainingTask trainingTask) {
        trainingTaskMapper.insert(trainingTask);
    }

    public void updateTrainingTask(TrainingTask trainingTask) {
        trainingTaskMapper.update(trainingTask);
    }

    public void deleteTrainingTaskById(Integer id) {
        trainingTaskMapper.deleteById(id);
    }
}
