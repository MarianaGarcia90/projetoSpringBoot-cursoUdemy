package com.udemy.projeto.dto;

import com.udemy.projeto.model.Category;

public class CategoryDTO {
    private static final long serialVersionUID = 1l;

    private Integer id;
    private String name;

    public CategoryDTO() {

    }

    public CategoryDTO(Category category) {
        id = category.getId();
        name = category.getName();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
