package com.udemy.projeto.controller;

import com.udemy.projeto.model.Client;
import com.udemy.projeto.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        //? pq pode ser de qlq tipo e ele pode encontrar ou não

        Client client = clientService.find(id);
        return ResponseEntity.ok().body(client); //ocorreu tudo bem então mostre o objeto category
    }

}
