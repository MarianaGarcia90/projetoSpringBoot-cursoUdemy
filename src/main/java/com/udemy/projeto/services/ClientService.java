package com.udemy.projeto.services;

import com.udemy.projeto.model.Category;
import com.udemy.projeto.model.Client;
import com.udemy.projeto.repositoies.ClientRepository;
import com.udemy.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;

    public Client find(Integer id) {
        Optional<Client> obj = clientRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }
}
