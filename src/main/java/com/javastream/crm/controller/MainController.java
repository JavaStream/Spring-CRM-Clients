package com.javastream.crm.controller;

import com.javastream.crm.model.Client;
import com.javastream.crm.model.User;
import com.javastream.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private ClientRepository clientRepository;

    @Value("${upload.path}")
    private String uploadPath;

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
    public String add(
            @AuthenticationPrincipal User user,
            @RequestParam String name,
            @RequestParam String description, Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        Client client = new Client(name, description, user);

        if (file != null) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            } else {
                System.out.println("Файл найден на диске D!");
            }

            // Создаем уникальное имя файла
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            System.out.println(resultFilename);

            file.transferTo(new File(uploadPath + "/" + resultFilename));
            client.setFilename(resultFilename);
        }

        clientRepository.save(client);

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "main";
    }

}
