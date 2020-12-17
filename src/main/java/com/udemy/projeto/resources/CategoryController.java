package com.udemy.projeto.resources;

import com.udemy.projeto.domain.Category;
import com.udemy.projeto.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> find(@PathVariable Integer id){
    //? pq pode ser de qlq tipo e ele pode encontrar ou não

        Category category = categoryService.find(id);
        return ResponseEntity.ok().body(category); //ocorreu tudo bem então mostre o objeto category
    }
}
