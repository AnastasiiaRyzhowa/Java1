package com.hospital.ekg.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EKGJson {
    private int tick;
    private double value;
    private int type;
}
