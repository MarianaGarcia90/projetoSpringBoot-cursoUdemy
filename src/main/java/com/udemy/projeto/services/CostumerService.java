package com.udemy.projeto.services;

import com.udemy.projeto.dto.CostumerDTO;
import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.repositoies.CostumerRepository;
import com.udemy.projeto.services.exceptions.DataIntregityException;
import com.udemy.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Costumer update(Costumer costumer) {
        Costumer newCostumer = find(costumer.getId());
        updateData(newCostumer, costumer);
        return costumerRepository.save(newCostumer);
    }

    private void updateData(Costumer newCostumer, Costumer costumer) {
        newCostumer.setName(costumer.getName());
        newCostumer.setEmail(costumer.getEmail());
    } //para atualizar somente nome e email no update

    public void delete(Integer id) {
        find(id);
        try {
            costumerRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntregityException("Não é possível excluir um cliente que possui pedidos!");
        }
    }

    public List<Costumer> findAll() {
        return costumerRepository.findAll();
    }

    public Page<Costumer> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        return costumerRepository.findAll(pageRequest);
    }

    public Costumer fromDTO(CostumerDTO costumerDTO) {
        return new Costumer(costumerDTO.getId(), costumerDTO.getName(), costumerDTO.getEmail(), null, null);

    }
}
