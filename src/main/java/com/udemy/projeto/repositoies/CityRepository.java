package com.udemy.projeto.repositoies;

import com.udemy.projeto.model.Category;
import com.udemy.projeto.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
