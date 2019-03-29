package com.javastream.crm.controller;

import com.javastream.crm.model.Client;
import com.javastream.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/")
    public String output() {
        return "test";
    }

    @GetMapping("/print")
    public String print(@RequestParam(name="name", required = false, defaultValue = "Test") String name, Map<String, Object> model) {
        model.put("name", name);
        return "test2";
    }

    @GetMapping("/main")
    public String main(Map<String, Object> model) {
        Iterable<Client> clientList = clientRepository.findAll();
        model.put("clients", clientList);
        return "main";
    }

    @PostMapping
    public String add(@RequestParam String name, @RequestParam String description, Map<String, Object> model) {
        Client client = new Client(name, description);
        clientRepository.save(client);
        return "main";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam String filter, Map<String, Object> model) {

        if (filter.isEmpty()) {
            Iterable<Client> clientList = clientRepository.findAll();
            model.put("clients", clientList);
        } else {
            List<Client> clientList = clientRepository.findByName(filter);
            model.put("clients", clientList);
        }

        return "main";
    }
}
