package com.udemy.projeto;

import com.udemy.projeto.model.*;
import com.udemy.projeto.model.enums.ClientType;
import com.udemy.projeto.repositoies.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

//CommandLineRunner permite adicionar mais um método para auxiliar o programa
@SpringBootApplication
public class ProjetoApplication implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private StateRepository stateRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CostumerRepository costumerRepository;
    @Autowired
    private AddressRepository addressRepository;

    public static void main(String[] args) {
        SpringApplication.run(ProjetoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Category cat1 = new Category(null, "Informática");
        Category cat2 = new Category(null, "Escritório");

        Product p1 = new Product(null, "Computador", 2000.00);
        Product p2 = new Product(null, "Impressora", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
        cat2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(cat1));
        p2.getCategories().addAll(Arrays.asList(cat1, cat2));
        p3.getCategories().addAll(Arrays.asList(cat1));

        categoryRepository.saveAll(Arrays.asList(cat1, cat2));
        productRepository.saveAll(Arrays.asList(p1, p2, p3));

        State est1 = new State(null, "Minas Gerais");
        State est2 = new State(null, "São Paulo");

        City c1 = new City(null, "Uberlândia", est1);
        City c2 = new City(null, "São Paulo", est2);
        City c3 = new City(null, "Campinas", est2);

        est1.getCities().addAll(Arrays.asList(c1));
        est2.getCities().addAll(Arrays.asList(c2, c3));

        stateRepository.saveAll(Arrays.asList(est1, est2));
        cityRepository.saveAll(Arrays.asList(c1, c2, c3));

        Costumer cli1 = new Costumer(null, "Maria Silva", "mariasilva@gmail.com", "330.638.005-98", ClientType.PESSOAFISICA);
        cli1.getPhone().addAll(Arrays.asList("3328-7780", "4581-7821"));

        Address e1 = new Address(null, "Rua Flores", "885", "Apto 12A", "Jordãnia", "13214-658", cli1, c1);
        Address e2 = new Address(null, "Av. Matos", "5032", "Sala 10 Primeiro andar", "Centro", "13000-018", cli1, c2);

        cli1.getAddresses().addAll(Arrays.asList(e1, e2));

        costumerRepository.saveAll(Arrays.asList(cli1));
        addressRepository.saveAll(Arrays.asList(e1, e2));
    }
}
