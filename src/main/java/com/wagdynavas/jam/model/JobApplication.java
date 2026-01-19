package com.wagdynavas.jam.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private String id;

    @Column
    private String jobTitle;

    @Column
    private String companyName;

    @Column
    private String url;

    @Column
    private String description;

    @Column
    private String interviewProcess;

}
