package com.udemy.projeto;

import com.udemy.projeto.model.*;
import com.udemy.projeto.model.enums.CostumerType;
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


    public static void main(String[] args) {
        SpringApplication.run(ProjetoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {


    }
}
