package com.javastream.crm.controller;

import com.javastream.crm.model.Client;
import com.javastream.crm.model.User;
import com.javastream.crm.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Set;
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
            @Valid Client client,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file) throws IOException {
        client.setManager(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtil.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("client", client);
        } else {

            saveFile(client, file);

            model.addAttribute("client", null);

            clientRepository.save(client);
        }

        Iterable<Client> clients = clientRepository.findAll();
        model.addAttribute("clients", clients);

        return "main";
    }

    private void saveFile(@Valid Client client, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
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
    }

    @GetMapping("/user-clients/{user}")
    public String userClients(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Client client
    ) {
        Set<Client> clients = user.getClients();
        model.addAttribute("clients", clients);
        model.addAttribute("client", client);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userClients";
    }

    @PostMapping("/user-clients/{user}")
    public String updateClient(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Client client,
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (client.getManager().equals(currentUser)) {
            if (!StringUtils.isEmpty(name)) {
                client.setName(name);
            }

            if (!StringUtils.isEmpty(description)) {
                client.setDescription(description);
            }

            saveFile(client, file);

            clientRepository.save(client);

        }
        return "redirect:/user-clients/" + user;
    }



}
