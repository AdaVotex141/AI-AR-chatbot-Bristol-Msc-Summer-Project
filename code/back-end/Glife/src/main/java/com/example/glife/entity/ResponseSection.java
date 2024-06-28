package com.example.glife.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResponseSection {
    String responseType;
    String text;
    List<String> labels=new ArrayList<>();

}
