package com.hospital.ekg.model;

import java.time.LocalDate;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "patients")
@ToString(exclude = {"surveys"})
public class PatientEntity{
    
    @Id
    @GeneratedValue
    private int id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String middleName;

    @Column
    private LocalDate dateOfBirth;


    @JsonIgnore
    @OneToMany(mappedBy = "patient",
                fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<SurveyEntity> surveys;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="doctorUd", nullable = false)
    @JsonIgnore
    private DoctorEntity doctor;

    

}
