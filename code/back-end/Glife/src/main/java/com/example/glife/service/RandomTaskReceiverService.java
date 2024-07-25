package com.example.glife.service;

import com.example.glife.common.R;
import com.example.glife.entity.RandomTask;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RandomTaskReceiverService {

    public R<String> init(HttpServletRequest request);

    public R<String> add(HttpServletRequest request);

    public R<Long> MessageQueueLength(HttpServletRequest request);

}
