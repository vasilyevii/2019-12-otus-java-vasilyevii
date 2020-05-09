package ru.otus.controllers;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import ru.otus.core.model.User;
import ru.otus.front.FrontendService;


@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final FrontendService frontendService;
    private final SimpMessagingTemplate template;

    public UserController(FrontendService frontendService, SimpMessagingTemplate template) {
        this.frontendService = frontendService;
        this.template = template;
    }

    @MessageMapping("/createUser")
    public void createUser(User user) {
        logger.info("got user: {}", user);
        frontendService.saveUser(user, this::userWasCreated);
    }

    public void userWasCreated(User user) {
        logger.info("create user: {}", user);
        template.convertAndSend("/topic/refreshUserList", user);
    }

}
