package com.hospital.ekg.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EkgDTO {
    private int id;
    private int tick;
    private double value;
    private String type;
}
