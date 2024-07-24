package com.example.glife.service.impl;

import com.alibaba.druid.sql.visitor.functions.Length;
import com.example.glife.common.MessageWsHandler;
import com.example.glife.common.R;
import com.example.glife.entity.RandomTask;
import com.example.glife.entity.SystemRoutine;
import com.example.glife.entity.User;
import com.example.glife.mapper.SystemRoutineMapper;
import com.example.glife.service.RandomTaskReceiverService;
import com.example.glife.service.SystemRoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

import static com.example.glife.common.RedisConstants.USER_MESSAGES;

@Service
public class RandomTaskReceiverServiceImp implements RandomTaskReceiverService {

    @Autowired
    SystemRoutineService systemRoutineService;

    @Autowired
    MessageWsHandler messageWsHandler;

    @Autowired
    StringRedisTemplate template;

//    @Resource
//    SystemRoutineMapper systemRoutineMapper;


    public R<String> init(HttpServletRequest request){

        Long userID = getUserID(request);
        if(!userID.equals(Long.valueOf(0))){
            WebSocketSession webSocketSession = MessageWsHandler.userSessions.get(userID);

            if (webSocketSession != null) {
                String userIdStr = messageWsHandler.getUserIdFromSession(webSocketSession);
                if (userIdStr == null) {
                    return R.error("Invalid WebSocket session");
                }

                String task = template.opsForList().leftPop(USER_MESSAGES + userID);
                if (task != null) {
                    messageWsHandler.sendTaskToOneUser(webSocketSession, task);
                    return R.success("Task sent to WebSocket");
                } else {
                    return R.success("No tasks found");
                }
            } else {
                return R.error("WebSocket session not found for user");
            }
        }else{
            return R.error("Can't reach User ID");
        }
    }

    public R<String> add(HttpServletRequest request, String task){
        //task
        SystemRoutine routine = taskParse(task);
        Long userID = getUserID(request);
        if(!userID.equals(Long.valueOf(0))){
            return R.error("Can't get UserID");
        }
        routine.setUserid(userID);
        systemRoutineService.addFromRandomTask(request, routine);

        return R.success("add success!");
    }

    public R<Long> MessageQueueLength(HttpServletRequest request) {
        Long userID = getUserID(request);
        Long lengthLong = template.opsForList().size(USER_MESSAGES + userID);
        if (lengthLong != null) {
            if (lengthLong == 0) {
                return R.success(0L);
            } else {
                return R.success(lengthLong);
            }
        } else {
            return R.error("Doesn't have queue");
        }
    }


    private Long getUserID(HttpServletRequest request){
        HttpSession httpSession = request.getSession(false);
        User user = (User) httpSession.getAttribute("user");
        if(user != null){
            return user.getId();
        }
        return Long.valueOf(0);

    }

    private SystemRoutine taskParse(String task){
        SystemRoutine routine = new SystemRoutine();

        String title = parseTitle(task);
        int schedule = parseSchedule(task);

        routine.setType(1);
        routine.setCreateTime(LocalDateTime.now());
        routine.setSchedule(schedule);
        routine.setContent(title);
        return routine;
    }

    private String parseTitle(String task){
        String[] parts = task.split("\n");
        if (parts.length > 0) {
            String titleLine = parts[1];//
            return titleLine.substring(titleLine.indexOf(':') + 1).trim();
        }
        return null;
    }

    private int parseSchedule(String task) {
        if (task.contains("Schedule: daily")) {
            return 0;
        } else if (task.contains("Schedule: weekly")) {
            return 1;
        } else if (task.contains("Schedule: monthly")) {
            return 2;
        }
        return -1;
    }

}
