package com.projeto.java.listatarefas.dto;

import com.projeto.java.listatarefas.model.Tarefa;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {

    @NotBlank(message = "O campo descrição é obrigatório")
    private String descricao;

    public static TarefaDTO convert(Tarefa Tarefa) {

        TarefaDTO TarefaDTO = new TarefaDTO();
        TarefaDTO.setDescricao(Tarefa.getDescricao());

        return TarefaDTO;
    }
}
