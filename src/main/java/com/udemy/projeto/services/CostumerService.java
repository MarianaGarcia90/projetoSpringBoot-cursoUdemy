package com.udemy.projeto.services;

import com.udemy.projeto.dto.CostumerDTO;
import com.udemy.projeto.dto.NewCostumerDTO;
import com.udemy.projeto.model.Address;
import com.udemy.projeto.model.City;
import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.model.enums.CostumerType;
import com.udemy.projeto.repositoies.AddressRepository;
import com.udemy.projeto.repositoies.CostumerRepository;
import com.udemy.projeto.services.exceptions.DataIntregityException;
import com.udemy.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CostumerService {

    @Autowired
    CostumerRepository costumerRepository;

    @Autowired
    AddressRepository addressRepository;

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

    public Costumer fromNewDTO(NewCostumerDTO newCostumerDTO) {
        Costumer costumer = new Costumer(null, newCostumerDTO.getName(), newCostumerDTO.getEmail(), newCostumerDTO.getCpfOrCnpj(), CostumerType.toEnum(newCostumerDTO.getType()));
        City city = new City(newCostumerDTO.getCityId(), null, null);
        Address address = new Address(null, newCostumerDTO.getAddress(), newCostumerDTO.getNumber(), newCostumerDTO.getComplement(), newCostumerDTO.getNeighborhood(), newCostumerDTO.getZipcode(), costumer, city);
        costumer.getAddresses().add(address);
        costumer.getPhone().add(newCostumerDTO.getPhone1());
        if(newCostumerDTO.getPhone2()!=null) {
            costumer.getPhone().add(newCostumerDTO.getPhone2());
        }
        if(newCostumerDTO.getPhone3()!=null) {
            costumer.getPhone().add(newCostumerDTO.getPhone3());
        }
        return costumer;
    }

    @Transactional
    public Costumer insert(Costumer costumer) {
        costumer.setId(null);
        costumer = costumerRepository.save(costumer);
        addressRepository.saveAll(costumer.getAddresses());
        return costumer;
    }
}
