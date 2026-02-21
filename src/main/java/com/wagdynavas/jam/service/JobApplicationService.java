package com.wagdynavas.jam.service;

import com.wagdynavas.jam.dto.JobApplicationRequest;
import com.wagdynavas.jam.dto.JobApplicationResponse;
import com.wagdynavas.jam.error.APIErrorCode;
import com.wagdynavas.jam.error.JobApplicationException;
import com.wagdynavas.jam.model.JobApplication;
import com.wagdynavas.jam.repository.JobApplicationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobApplicationService {

    private final JobApplicationRepository jobApplicationRepository;

    public JobApplicationResponse getAllJobApplications() {
       List<JobApplication> jobApplication = jobApplicationRepository.findAll();

       return JobApplicationResponse.builder()
                .jobApplications(jobApplication)
                .build();
    }

    public JobApplicationResponse getJobApplicationById(String id) {
        Optional<JobApplication> jobApplication = jobApplicationRepository.findById(id);
        return JobApplicationResponse.builder()
                .jobApplications(singletonList(jobApplication.orElseGet(() -> {
                    String errorMessage  = "Unable to find Job Application - ID: " + id;
                    log.error(errorMessage);
                    throw  new JobApplicationException(APIErrorCode.NOT_FOUND, errorMessage);
                })))
                .build();

    }

    public void createJobApplication(JobApplicationRequest applicationRequest) {
        jobApplicationRepository.save(applicationRequest.jobApplication());
    }
}
