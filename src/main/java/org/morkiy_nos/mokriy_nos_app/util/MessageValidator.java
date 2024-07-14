package org.morkiy_nos.mokriy_nos_app.util;

import org.morkiy_nos.mokriy_nos_app.model.Message;
import org.morkiy_nos.mokriy_nos_app.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MessageValidator implements Validator {
    private final MessageService messageService;

    @Autowired
    public MessageValidator(MessageService messageService) {
        this.messageService = messageService;
    }
    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.equals(Message.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Message message = (Message) target;

        if(messageService.getMessageByTitle(message.getTitle()) != null) {
            errors.rejectValue("title", "message.exists", "Данное сообщение уже отправлено");
        }
    }
}
