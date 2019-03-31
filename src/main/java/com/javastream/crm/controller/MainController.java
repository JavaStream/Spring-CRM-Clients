package com.javastream.crm.controller;

import com.javastream.crm.model.Client;
import com.javastream.crm.model.User;
import com.javastream.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        return "index";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false) String filter, Model model) {
        Iterable<Client> clients = clientRepository.findAll();

        if (filter != null && !filter.isEmpty()) {
            clients = clientRepository.findByName(filter);
        } else {
            clients = clientRepository.findAll();

        }
        model.addAttribute("clients", clients);
        model.addAttribute("filter", filter);
        return "main";
    }

    @PostMapping("/main")
    public String add(@AuthenticationPrincipal User user, @RequestParam String name, @RequestParam String description) {
        Client client = new Client(name, description, user);
        clientRepository.save(client);
        return "main";
    }

}
