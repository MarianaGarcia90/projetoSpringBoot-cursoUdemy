package com.udemy.projeto.controller;

import com.udemy.projeto.dto.CategoryDTO;
import com.udemy.projeto.dto.CostumerDTO;
import com.udemy.projeto.model.Category;
import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.services.CostumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/costumers")
public class CostumerController {

    @Autowired
    private CostumerService costumerService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Costumer> find(@PathVariable Integer id) {
        Costumer costumer = costumerService.find(id);
        return ResponseEntity.ok().body(costumer); //ocorreu tudo bem então mostre o objeto category
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@Valid @RequestBody CostumerDTO costumerDTO, @PathVariable Integer id) {
        Costumer costumer = costumerService.fromDTO(costumerDTO);
        costumer.setId(id);
        costumer = costumerService.update(costumer);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        costumerService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CostumerDTO>> findAll() {
        List<Costumer> costumerList = costumerService.findAll();
        List<CostumerDTO> costumerDTOList = costumerList.stream().map(costumer -> new CostumerDTO(costumer)).collect(Collectors.toList());
        return ResponseEntity.ok().body(costumerDTOList);
    }

    @RequestMapping(value = "/page", method = RequestMethod.GET)
    public ResponseEntity<Page<CostumerDTO>> findPage(
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {
        Page<Costumer> costumerList = costumerService.findPage(page, linesPerPage, orderBy, direction);
        Page<CostumerDTO> costumerDTOList = costumerList.map(costumer -> new CostumerDTO(costumer));
        return ResponseEntity.ok().body(costumerDTOList);
    }

}
