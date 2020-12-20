package com.udemy.projeto;

import com.udemy.projeto.model.*;
import com.udemy.projeto.model.enums.ClientType;
import com.udemy.projeto.model.enums.PaymentState;
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
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Autowired
    private ItemRepository itemRepository;

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

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:30"), cli1, e1);
        Pedido ped2 = new Pedido(null, sdf.parse("10/10/2018 18:40"), cli1, e2);

        Payment pagto1 = new PaymentCard(null, PaymentState.QUITADO, ped1, 6);
        ped1.setPayment(pagto1);

        Payment pagto2 = new PaymentSlip(null, PaymentState.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
        ped2.setPayment(pagto2);

        cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));

        orderRepository.saveAll(Arrays.asList(ped1, ped2));
        paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));

        Item ip1 = new Item(ped1, p1, 0.00, 1, 2000.00);
        Item ip2 = new Item(ped1, p3, 0.00, 2, 80.00);
        Item ip3 = new Item(ped2, p2, 100.00, 1, 800.00);

        ped1.getItems().addAll(Arrays.asList(ip1,ip2));
        ped2.getItems().addAll(Arrays.asList(ip3));

        p1.getItems().addAll(Arrays.asList(ip1));
        p2.getItems().addAll(Arrays.asList(ip3));
        p3.getItems().addAll(Arrays.asList(ip2));

        itemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

    }
}
