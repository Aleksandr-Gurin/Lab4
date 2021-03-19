package com.example.weblab4.requests;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PointDTO {
    @NotNull private String x;
    @NotNull private String y;
    @NotNull private String r;
}

