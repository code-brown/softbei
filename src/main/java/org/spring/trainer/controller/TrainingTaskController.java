package org.spring.trainer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.trainer.entity.TrainingTask;
import org.spring.trainer.service.TrainingTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/training-tasks")
@CrossOrigin(origins = {"http://localhost:3000", "http://36.138.114.105:30918"})
public class TrainingTaskController {

    private static final Logger logger = LoggerFactory.getLogger(TrainingTaskController.class);

    private final TrainingTaskService trainingTaskService;

    @Autowired
    public TrainingTaskController(TrainingTaskService trainingTaskService) {
        this.trainingTaskService = trainingTaskService;
    }

    @GetMapping
    public ResponseEntity<List<TrainingTask>> getAllTrainingTasks() {
        try {
            List<TrainingTask> tasks = trainingTaskService.findAllTrainingTasks();
            return ResponseEntity.ok(tasks);
        } catch (Exception e) {
            logger.error("Error fetching training tasks", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainingTask> getTrainingTaskById(@PathVariable Integer id) {
        try {
            TrainingTask task = trainingTaskService.findTrainingTaskById(id);
            if (task != null) {
                return ResponseEntity.ok(task);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            logger.error("Error fetching training task by id", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping
    public ResponseEntity<Void> addTrainingTask(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("type") Integer type,
            @RequestParam("startTime") String startTime,
            @RequestParam("endTime") String endTime,
            @RequestParam("subjects") String subjects,
            @RequestParam("coverImage") MultipartFile coverImage) {
        try {
            TrainingTask trainingTask = new TrainingTask();
            trainingTask.setTitle(title);
            trainingTask.setDescription(description);
            trainingTask.setType(type);
            trainingTask.setStartTime(startTime);
            trainingTask.setEndTime(endTime);
            trainingTask.setSubjects(subjects);

            // 保存文件到文件系统或数据库
            String fileName = coverImage.getOriginalFilename();
            // 你可以在这里添加保存文件的逻辑
            logger.info("File uploaded successfully: " + fileName);

            trainingTaskService.addTrainingTask(trainingTask);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            logger.error("Error adding training task", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTrainingTask(@PathVariable Integer id, @RequestBody TrainingTask trainingTask) {
        try {
            trainingTask.setId(id);
            trainingTaskService.updateTrainingTask(trainingTask);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error updating training task", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrainingTaskById(@PathVariable Integer id) {
        try {
            trainingTaskService.deleteTrainingTaskById(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Error deleting training task", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
