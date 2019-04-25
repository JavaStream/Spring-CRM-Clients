package com.javastream.crm.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotBlank(message = "Please fill the Name")
    private String name;

    @NotBlank(message = "Please fill the description")
    @Length(max = 2048, message = "Descripton too long (more then 2Kb)")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)   // У одного менеджера может быть много клиентов
    @JoinColumn(name = "user_id")         // Поле в базе данных будет называться user_id, а не manager_id
    private User manager;

    private String filename;

    public Client() {
    }

    public Client(String name, String description, User user) {
        this.manager = user;
        this.name = name;
        this.description = description;
    }

    public String getManagerName() {
        return manager != null ? manager.getUsername() : "<no manager>";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getManager() {
        return manager;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
