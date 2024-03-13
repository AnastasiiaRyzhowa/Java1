package com.hospital.ekg.model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "doctors")
@ToString(exclude = {"patients"})
@EqualsAndHashCode
public class DoctorEntity{
    @Id
    @GeneratedValue
    private long id;
    
    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @ManyToMany
    private Set<RolesEntity> roles;


    @JsonIgnore
    @Transient
    @OneToMany(mappedBy = "doctor",
                fetch = FetchType.EAGER,
                cascade = CascadeType.ALL)
    private Set<PatientEntity> patients;
}
