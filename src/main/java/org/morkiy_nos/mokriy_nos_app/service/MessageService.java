package org.morkiy_nos.mokriy_nos_app.service;

import org.morkiy_nos.mokriy_nos_app.model.Message;
import org.morkiy_nos.mokriy_nos_app.model.MessageType;
import org.morkiy_nos.mokriy_nos_app.repository.MessageRepository;
import org.morkiy_nos.mokriy_nos_app.util.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void save(Message message) {
        message.setMarked(false);
        message.setCreatedDate(LocalDateTime.now());
        messageRepository.save(message);
    }

    public boolean existsById(int id) {
        return messageRepository.existsById(id);
    }

    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageByTitle(String title) {
        return messageRepository.findByTitle(title).orElse(null);
    }

    public List<Message> sort(Sort choice) {
        org.springframework.data.domain.Sort.Direction direction = (choice.getDate().equals("old")) ?
                org.springframework.data.domain.Sort.Direction.ASC :
                org.springframework.data.domain.Sort.Direction.DESC;
        if(choice.getValue().equalsIgnoreCase("Все")) {
            return messageRepository.findAll(org.springframework.data.domain.Sort.by(direction, "createdDate"));
        }
        return messageRepository.findByType(MessageType.valueOf(choice.getValue()),
                org.springframework.data.domain.Sort.by(direction, "createdDate"));
    }

    public List<Message> sortLiked(Sort choice) {
        org.springframework.data.domain.Sort.Direction direction = (choice.getDate().equals("old")) ?
                org.springframework.data.domain.Sort.Direction.ASC :
                org.springframework.data.domain.Sort.Direction.DESC;
        if(choice.getValue().equalsIgnoreCase("Все")) {
            return messageRepository.findByIsMarked(true, org.springframework.data.domain.Sort.by(direction, "createdDate"));
        }
        return messageRepository.findByIsMarkedAndType(true, MessageType.valueOf(choice.getValue()),
                org.springframework.data.domain.Sort.by(direction, "createdDate"));
    }

    public void like(int id) {
        Message message = messageRepository.findById(id).orElse(null);
        if(message != null) {
            message.setMarked(!message.isMarked());
            messageRepository.save(message);
        } else throw new IllegalArgumentException("We dont have message dude");
    }

    public List<Message> getAllLiked() {
        return messageRepository.findByIsMarked(true);
    }


    public void delete(int id) {
        messageRepository.deleteById(id);
    }
}
