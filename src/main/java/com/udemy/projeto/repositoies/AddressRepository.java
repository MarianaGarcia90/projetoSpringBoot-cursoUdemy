package com.udemy.projeto.repositoies;

import com.udemy.projeto.model.Address;
import com.udemy.projeto.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
