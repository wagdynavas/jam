package com.wagdynavas.jam.repository;

import com.wagdynavas.jam.model.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository extends JpaRepository<JobApplication, String> {
}
