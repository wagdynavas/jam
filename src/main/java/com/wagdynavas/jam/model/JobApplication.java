package com.wagdynavas.jam.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "JOB_APPLICATION")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "JAM_ID")
    private String id;

    @Column(name = "JAM_JOB_TITLE")
    private String jobTitle;

    @Column(name = "JAM_COMPANY_NAME")
    private String companyName;

    @Column(name = "JAM_URL")
    private String url;

    @Column(name = "JAM_DESCRIPTION")
    private String description;

    @Column(name = "JAM_INTERVIEW_PROCESS")
    private String interviewProcess;

}
