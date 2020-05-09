package ru.otus.db.handlers;

import org.springframework.stereotype.Component;
import ru.otus.common.Serializers;
import ru.otus.core.model.User;
import ru.otus.core.service.DBServiceUser;
import ru.otus.messagesystem.Message;
import ru.otus.messagesystem.MessageType;
import ru.otus.messagesystem.RequestHandler;

import java.util.Optional;

@Component
public class GetUserDataRequestHandler implements RequestHandler {
    private final DBServiceUser dbServiceUser;

    public GetUserDataRequestHandler(DBServiceUser dbServiceUser) {
        this.dbServiceUser = dbServiceUser;
    }

    @Override
    public Optional<Message> handle(Message msg) {
        User user = Serializers.deserialize(msg.getPayload(), User.class);
        dbServiceUser.saveUser(user);
        return Optional.of(new Message(msg.getTo(), msg.getFrom(), msg.getId(), MessageType.CREATE_USER.getValue(), Serializers.serialize(user)));
    }
}
