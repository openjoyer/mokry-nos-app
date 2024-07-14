package org.morkiy_nos.mokriy_nos_app.controller;

import org.morkiy_nos.mokriy_nos_app.model.Message;
import org.morkiy_nos.mokriy_nos_app.model.MessageType;
import org.morkiy_nos.mokriy_nos_app.service.MessageService;
import org.morkiy_nos.mokriy_nos_app.util.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin")
public class AdminController {
    private final MessageType[] types = MessageType.values();
    private final MessageService messageService;

    @Autowired
    public AdminController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String adminPage(Model model) {
        if(!model.containsAttribute("messages")) {
            model.addAttribute("messages", messageService.getAllMessages());
        }
        if(!model.containsAttribute("sortChoice")) {
            model.addAttribute("sortChoice", new Sort());
        }
        model.addAttribute("sorts", types);
        model.addAttribute("liked", messageService.getAllLiked());

        return "admin/admin_page";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "admin/login_page";
    }

    @GetMapping("/sort")
    public String sort(@ModelAttribute("sortChoice") Sort sortChoice, RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("messages", messageService.sort(sortChoice));
        redirectAttributes.addFlashAttribute("liked", messageService.sortLiked(sortChoice));
        redirectAttributes.addFlashAttribute("sortChoice", sortChoice);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, Model model) {
        Message message = messageService.getMessageById(id);
        model.addAttribute("message", message);
        return "admin/show";
    }

    @PatchMapping("/{id}")
    public String like(@PathVariable("id") int id) {
        messageService.like(id);
        return "redirect:/admin/{id}";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        messageService.delete(id);
        return "redirect:/admin";
    }
}
