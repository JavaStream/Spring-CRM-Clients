package com.javastream.crm.repository;

import com.javastream.crm.model.Client;
import org.springframework.data.repository.CrudRepository;


public interface ClientRepository extends CrudRepository<Client, Integer> {

}
