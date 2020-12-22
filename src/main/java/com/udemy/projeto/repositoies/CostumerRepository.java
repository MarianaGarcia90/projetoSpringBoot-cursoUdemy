package com.udemy.projeto.repositoies;

import com.udemy.projeto.model.Costumer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CostumerRepository extends JpaRepository<Costumer, Integer> {

    @Transactional(readOnly = true)
    Costumer findByEmail(String email);
}
