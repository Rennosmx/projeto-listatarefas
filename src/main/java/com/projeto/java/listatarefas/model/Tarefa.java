package com.projeto.java.listatarefas.model;

import com.projeto.java.listatarefas.dto.TarefaDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name="tarefa")
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String descricao;

    public static Tarefa convert(TarefaDTO tarefaDTO) {
        Tarefa Tarefa = new Tarefa();
        Tarefa.setDescricao(tarefaDTO.getDescricao());
        return Tarefa;
    }

}
