package com.wagdynavas.jam.controller;

import com.wagdynavas.jam.dto.JobApplicationRequest;
import com.wagdynavas.jam.dto.JobApplicationResponse;
import com.wagdynavas.jam.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/job/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping
    public ResponseEntity<JobApplicationResponse> getJobApplications() {
        return ResponseEntity.ok(jobApplicationService.getAllJobApplications());
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobApplicationResponse> getJobApplication(@PathVariable("id") String id) {
        return ResponseEntity.ok(jobApplicationService.getJobApplicationById(id));
    }

    @PostMapping
    public ResponseEntity<String> createJobApplication(@RequestBody JobApplicationRequest applicationRequest) {
        jobApplicationService.createJobApplication(applicationRequest);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
