package com.example.glife.entity;

import lombok.Data;
import org.springframework.data.geo.Point;

@Data
public class Marker {
    Point point;
    String name;
}
