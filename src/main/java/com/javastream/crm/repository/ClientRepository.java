package com.javastream.crm.repository;

import com.javastream.crm.model.Client;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface ClientRepository extends CrudRepository<Client, Integer> {
    List<Client> findByName(String name);
}
