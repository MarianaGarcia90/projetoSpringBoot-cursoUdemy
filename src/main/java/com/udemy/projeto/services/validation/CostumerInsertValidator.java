package com.udemy.projeto.services.validation;

import com.udemy.projeto.controller.exception.FieldMessage;
import com.udemy.projeto.dto.NewCostumerDTO;
import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.model.enums.CostumerType;
import com.udemy.projeto.repositoies.CostumerRepository;
import com.udemy.projeto.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CostumerInsertValidator implements ConstraintValidator<CostumerInsert, NewCostumerDTO> {

    @Autowired
    private CostumerRepository costumerRepository;

    @Override
    public void initialize(CostumerInsert ann) {
    }

    @Override
    public boolean isValid(NewCostumerDTO newCostumerDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (newCostumerDTO.getType().equals(CostumerType.PESSOAFISICA.getCod()) && !BR.isValidCPF(newCostumerDTO.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CPF inválido"));
        }

        if (newCostumerDTO.getType().equals(CostumerType.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(newCostumerDTO.getCpfOrCnpj())) {
            list.add(new FieldMessage("cpfOrCnpj", "CNPJ inválido"));
        }

        Costumer aux = costumerRepository.findByEmail(newCostumerDTO.getEmail());
        if(aux!=null) {
            list.add(new FieldMessage("email", "Email já cadastrado"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage())
                    .addPropertyNode(e.getFieldMessage()).addConstraintViolation();
        }
        return list.isEmpty();
    }
}
