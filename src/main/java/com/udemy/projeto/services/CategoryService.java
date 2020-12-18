package com.udemy.projeto.services;

import com.udemy.projeto.model.Category;
import com.udemy.projeto.repositoies.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {

    @Autowired //automaticamente instanciada pelo spring
    private CategoryRepository categoryRepository;

    public Category find(Integer id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElse(null);
    }
}
