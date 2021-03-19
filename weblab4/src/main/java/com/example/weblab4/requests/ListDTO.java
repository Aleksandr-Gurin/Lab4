package com.example.weblab4.requests;

import lombok.Data;

import java.util.List;

@Data
public class ListDTO {
    private long id;
    private List<PointDTO> points;
}
