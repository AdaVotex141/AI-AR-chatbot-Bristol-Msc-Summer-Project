package com.example.glife.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Response {
    String error;
    List<ResponseSection> ResponseSectionList = new ArrayList<>();
}
