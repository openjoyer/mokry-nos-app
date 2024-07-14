package org.morkiy_nos.mokriy_nos_app.controller;

import jakarta.validation.Valid;
import org.morkiy_nos.mokriy_nos_app.model.Message;
import org.morkiy_nos.mokriy_nos_app.model.MessageType;
import org.morkiy_nos.mokriy_nos_app.service.MessageService;
import org.morkiy_nos.mokriy_nos_app.util.MessageValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
    private final MessageType[] types = MessageType.values();
    private final MessageService messageService;
    private final MessageValidator messageValidator;

    @Autowired
    public UserController(MessageService messageService, MessageValidator messageValidator) {
        this.messageService = messageService;
        this.messageValidator = messageValidator;
    }

    @PostMapping("/new")
    public String newPost(@ModelAttribute("message") @Valid Message message,
                          BindingResult bindingResult,
                          Model model) {
        messageValidator.validate(message, bindingResult);
        if (bindingResult.hasErrors()) {
            model.addAttribute("types", types);
            return "user/new";
        }
        messageService.save(message);
        return "redirect:/?success";
    }

    @GetMapping
    public String index() {
        return "user/index";
    }

    @GetMapping("/new")
    public String newPage(Model model) {
        model.addAttribute("message", new Message());
        model.addAttribute("types", types);
        return "user/new";
    }
}
