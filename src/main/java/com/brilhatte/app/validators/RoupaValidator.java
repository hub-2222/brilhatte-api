package com.brilhatte.app.validators;

import com.brilhatte.app.dtos.RoupaDTO;
import com.brilhatte.app.infra.exception.BusinessException;
import org.springframework.stereotype.Component;

@Component
public class RoupaValidator {
    public void validateFields(RoupaDTO roupa) {
        if (roupa.getNome() == null || roupa.getNome().isEmpty()) {
            throw new BusinessException("O nome da roupa é obrigatório");
        }

        if (roupa.getPedrasVinculadas().isEmpty()) {
            throw new BusinessException("A roupa deve ter pelo menos uma pedra vinculada");
        }
    }
}
