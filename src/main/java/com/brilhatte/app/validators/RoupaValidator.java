package com.brilhatte.app.validators;

import com.brilhatte.app.dtos.PedraVinculadaDTO;
import com.brilhatte.app.dtos.RoupaDTO;
import com.brilhatte.app.infra.exception.BusinessException;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Component
public class RoupaValidator {
    public void validateFields(RoupaDTO roupa) {
        if (roupa.getNome() == null || roupa.getNome().isEmpty()) {
            throw new BusinessException("O nome da roupa é obrigatório");
        }

        if (roupa.getPedrasVinculadas().isEmpty()) {
            throw new BusinessException("A roupa deve ter pelo menos uma pedra vinculada");
        }

        validatePedrasDuplicadas(roupa);
    }

    public void validatePedrasDuplicadas(RoupaDTO roupa) {
        Set<Long> idsUnicos = new HashSet<>();
        for (PedraVinculadaDTO pedra : roupa.getPedrasVinculadas()) {
            if (Objects.isNull(pedra.getQuantidade())) {
                throw new BusinessException("A quantidade da pedra é obrigatória");
            }

            if (!idsUnicos.add(pedra.getId())) {
                throw new BusinessException("A roupa não pode ter pedras duplicadas");
            }
        }
    }
}
