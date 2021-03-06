package ru.otus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import ru.otus.core.service.DbServiceUserImpl;
import ru.otus.db.handlers.GetUserDataRequestHandler;
import ru.otus.front.FrontendService;
import ru.otus.front.FrontendServiceImpl;
import ru.otus.front.handlers.GetUserDataResponseHandler;
import ru.otus.hibernate.dao.UserDaoHibernate;
import ru.otus.messagesystem.*;

@Configuration
@EnableWebSocketMessageBroker
public class AppConfig implements WebSocketMessageBrokerConfigurer {

    private static final String FRONTEND_SERVICE_CLIENT_NAME = "frontendService";
    private static final String DATABASE_SERVICE_CLIENT_NAME = "databaseService";

    @Bean
    public MessageSystem messageSystem() {
        return new MessageSystemImpl();
    }

    @Bean
    public MsClient databaseMsClient() {
        MessageSystem messageSystem = messageSystem();
        MsClient databaseMsClient = new MsClientImpl(DATABASE_SERVICE_CLIENT_NAME, messageSystem);
        databaseMsClient.addHandler(MessageType.CREATE_USER, getUserDataRequestHandler());
        messageSystem.addClient(databaseMsClient);
        return databaseMsClient;
    }

    @Bean
    public MsClient frontendMsClient() {
        MessageSystem messageSystem = messageSystem();
        MsClient frontendMsClient = new MsClientImpl(FRONTEND_SERVICE_CLIENT_NAME, messageSystem());
        frontendMsClient.addHandler(MessageType.CREATE_USER, getUserDataResponseHandler(frontendMsClient));
        messageSystem.addClient(frontendMsClient);
        return frontendMsClient;
    }

    @Bean
    public RequestHandler getUserDataRequestHandler() {
        return new GetUserDataRequestHandler(new DbServiceUserImpl(new UserDaoHibernate()));
    }

    @Bean
    public RequestHandler getUserDataResponseHandler(MsClient frontendMsClient) {
        return new GetUserDataResponseHandler(frontendService(frontendMsClient));
    }

    @Bean
    public FrontendService frontendService(MsClient frontendMsClient) {
        return new FrontendServiceImpl(frontendMsClient, DATABASE_SERVICE_CLIENT_NAME);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/gs-guide-websocket").withSockJS();
    }
}
