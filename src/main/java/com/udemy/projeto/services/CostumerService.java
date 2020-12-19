package com.udemy.projeto.services;

import com.udemy.projeto.model.Category;
import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.repositoies.CostumerRepository;
import com.udemy.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    CostumerRepository costumerRepository;

    public Costumer find(Integer id) {
        Optional<Costumer> obj = costumerRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Costumer.class.getName()));
    }
}
