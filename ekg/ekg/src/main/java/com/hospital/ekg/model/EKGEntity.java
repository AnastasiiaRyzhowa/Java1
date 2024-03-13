package com.hospital.ekg.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode
@Table(name = "ekg_results")
public class EKGEntity{
    
    @Id
    @GeneratedValue
    private int id;

//    @Column
//    private EKGTypesEnum ekgType;

    @Column
    private int number;

    @Column
    private double val;

    @Column
    private String type;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name="surveyId", nullable = false)
    @JsonIgnore
    private SurveyEntity survey;

}
