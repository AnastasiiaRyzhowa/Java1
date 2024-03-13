package com.hospital.ekg.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "surveys")
@ToString(exclude = {"ekgResults"})
public class SurveyEntity{
    
    @Id
    @GeneratedValue
    private int id;

    @Column
    private LocalDate dateSurvey;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="patientId", nullable = false)
    @JsonIgnore
    private PatientEntity patient;

    @JsonIgnore
    @Transient
    @OneToMany(mappedBy = "survey",
                fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<EKGEntity> ekgResults;

}
