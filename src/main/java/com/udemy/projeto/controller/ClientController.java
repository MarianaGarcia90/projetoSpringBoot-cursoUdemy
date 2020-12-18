package com.udemy.projeto.controller;

import com.udemy.projeto.model.Category;
import com.udemy.projeto.model.Client;
import com.udemy.projeto.services.CategoryService;
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

        Client category = clientService.find(id);
        return ResponseEntity.ok().body(category);
    }

}
