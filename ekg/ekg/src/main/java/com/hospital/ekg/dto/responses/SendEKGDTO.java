package com.hospital.ekg.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendEKGDTO {
    private double value;
    private int tick;
    private int surveyId;
    
}
