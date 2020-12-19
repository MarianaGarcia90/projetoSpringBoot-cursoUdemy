package com.udemy.projeto.repositoies;

import com.udemy.projeto.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Pedido, Integer> {

}
