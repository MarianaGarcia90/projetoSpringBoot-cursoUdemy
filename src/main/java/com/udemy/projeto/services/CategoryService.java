package com.udemy.projeto.services;

import com.udemy.projeto.dto.CategoryDTO;
import com.udemy.projeto.model.Category;
import com.udemy.projeto.repositoies.CategoryRepository;
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

//classe responsável por fazer a consulta no repositório
@Service
public class CategoryService {

    @Autowired //automaticamente instanciada pelo spring
    private CategoryRepository categoryRepository;

    public Category find(Integer id) {
        Optional<Category> obj = categoryRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Objeto não encontrado! Id: " + id + ", Tipo: " + Category.class.getName()));
    }

    public Category insert(Category category) {
        category.setId(null); //se já tiver Id é pq é atualização e não inserção
        return categoryRepository.save(category);
    }

    public Category update(Category category) {
        Category newCategory = find(category.getId());
        updateData(newCategory, category);
        return categoryRepository.save(newCategory);
    }

    private void updateData(Category newCategory, Category category) {
        newCategory.setName(category.getName());
    } //para atualizar somente nome e email no update

    public void delete(Integer id) {
        find(id);
        try {
            categoryRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DataIntregityException("Não é possível excluir uma categoria que possui produtos!");
        }
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Page<Category> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction),
                orderBy);
        return categoryRepository.findAll(pageRequest);
    }

    public Category fromDTO(CategoryDTO categoryDTO) {
        return new Category(categoryDTO.getId(), categoryDTO.getName());
    }
}
