package com.example.glife.service;


import com.example.glife.common.R;
import com.example.glife.entity.RandomTask;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RandomTaskService {

    public R<String> send(HttpServletRequest request, RandomTask randomTask);
    public R<List<RandomTask>> init(HttpServletRequest request);


}
