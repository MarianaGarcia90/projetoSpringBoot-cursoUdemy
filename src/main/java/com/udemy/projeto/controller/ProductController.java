package com.udemy.projeto.controller;

import com.udemy.projeto.controller.utils.URL;
import com.udemy.projeto.dto.ProductDTO;
import com.udemy.projeto.model.Product;
import com.udemy.projeto.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Product> find(@PathVariable Integer id) {
        Product product = productService.find(id);
        return ResponseEntity.ok().body(product); //ocorreu tudo bem então mostre o objeto category
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "name")String orderBy,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction) {
        String nomeDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> productList = productService.search(nomeDecoded, ids, page, linesPerPage, orderBy, direction);
        Page<ProductDTO> productDTOList = productList.map(product -> new ProductDTO(product));
        return ResponseEntity.ok().body(productDTOList);
    }

}
