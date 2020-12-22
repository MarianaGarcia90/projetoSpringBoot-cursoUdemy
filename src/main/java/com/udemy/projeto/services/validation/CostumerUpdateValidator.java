package com.udemy.projeto.services.validation;

import com.udemy.projeto.controller.exception.FieldMessage;
import com.udemy.projeto.dto.CostumerDTO;
import com.udemy.projeto.model.Costumer;
import com.udemy.projeto.model.enums.CostumerType;
import com.udemy.projeto.repositoies.CostumerRepository;
import com.udemy.projeto.services.validation.utils.BR;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CostumerUpdateValidator implements ConstraintValidator<CostumerUpdate, CostumerDTO> {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private CostumerRepository costumerRepository;

    @Override
    public void initialize(CostumerUpdate ann) {
    }

    @Override
    public boolean isValid(CostumerDTO costumerDTO, ConstraintValidatorContext context) {
        @SuppressWarnings("unchecked")
        Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
        Integer uriId = Integer.parseInt(map.get("id"));

        List<FieldMessage> list = new ArrayList<>();

        Costumer aux = costumerRepository.findByEmail(costumerDTO.getEmail());
        if (aux != null && !aux.getId().equals(uriId)) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldMessage())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
