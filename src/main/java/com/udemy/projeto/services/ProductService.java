package com.udemy.projeto.services;

import com.udemy.projeto.model.Category;
import com.udemy.projeto.model.Product;
import com.udemy.projeto.repositoies.CategoryRepository;
import com.udemy.projeto.repositoies.ProductRepository;
import com.udemy.projeto.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public Product find(Integer id) {
        Optional<Product> obj = productRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Product.class.getName()));
    }

    public Page<Product> search(String name, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        List<Category> categoryList = categoryRepository.findAllById(ids);
        return productRepository.findDistinctByNameContainingAndCategoriesIn(name, categoryList, pageRequest);

    }
}
