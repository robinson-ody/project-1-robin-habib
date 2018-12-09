package com.future.controller;

import com.future.model.Assignment;
import com.future.repository.AssignmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AssignmentController {


    @Autowired
    private AssignmentRepository assignmentRepository;

    @GetMapping("/assignment")
    public List<Assignment> getAllAssignment() {
        return (List<Assignment>) assignmentRepository.findAll();
    }

    @PostMapping("/assignment/create")
    public Assignment createAssignment(@RequestBody Assignment assignment) {
        return assignmentRepository.save(assignment);
    }

    @PutMapping("/assignment/{id}")
    public ResponseEntity<Assignment> updateAssignment(@PathVariable("id") String id, @RequestBody Assignment assignment) {
        Assignment assignmentData = assignmentRepository.findOne(id);
        if (assignment == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        assignmentData.setAssignmentId(assignment.getAssignmentId());
        assignmentData.setQty(assignment.getQty());
        assignmentData.setStatus(assignment.getStatus());
        assignmentData.setCreatedDate(assignment.getCreatedDate());
        assignmentData.setUpdatedDate(assignment.getUpdatedDate());

        Assignment updatedassignment = assignmentRepository.save(assignmentData);
        return new ResponseEntity<>(updatedassignment, HttpStatus.OK);
    }

    @DeleteMapping("/assignment/{id}")
    public ResponseEntity<String> deleteAssignment(@PathVariable("id") String id) {
        assignmentRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }



}
