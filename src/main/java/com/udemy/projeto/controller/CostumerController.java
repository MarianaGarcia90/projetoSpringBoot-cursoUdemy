package com.udemy.projeto.controller;

import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.services.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/clients")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id) {
        //? pq pode ser de qlq tipo e ele pode encontrar ou não

        Costumer costumer = costumerService.find(id);
        return ResponseEntity.ok().body(costumer); //ocorreu tudo bem então mostre o objeto category
    }

}
