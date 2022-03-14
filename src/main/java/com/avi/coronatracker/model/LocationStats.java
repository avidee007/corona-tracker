package com.avi.coronatracker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationStats {
    private String country;
    private String state;
    private int totalCase;
}
