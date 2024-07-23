package com.example.glife.controller;

import com.example.glife.common.MessageWsHandler;
import com.example.glife.common.R;
import com.example.glife.entity.User;
import com.example.glife.service.SystemRoutineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.glife.common.RedisConstants.USER_MESSAGES;

@RestController
@Slf4j
@RequestMapping("/randomTask")
public class RandomTaskReceiverController {

    @Autowired
    SystemRoutineService systemRoutineService;

    @Autowired
    MessageWsHandler messageWsHandler;

    @Autowired
    StringRedisTemplate template;

    @GetMapping("/init")
    public R<String> getTaskList(HttpServletRequest request) {
        HttpSession httpSession = request.getSession(false);
        if (httpSession == null || httpSession.getAttribute("userID") == null) {
            return R.error("User not logged in");
        }

        User user = (User) httpSession.getAttribute("user");
        Long userID = user.getId();
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
    }

    @PostMapping("/add")
    public R<String> add(HttpServletRequest request, String task){


        return null;
    }

}
