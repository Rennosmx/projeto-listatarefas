package com.projeto.java.listatarefas.service;

import com.projeto.java.listatarefas.dto.TarefaDTO;
import com.projeto.java.listatarefas.model.Tarefa;
import com.projeto.java.listatarefas.repository.TarefaRepository;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;

    public List<TarefaDTO> listarTarefas(){

        List<Tarefa> tarefas = tarefaRepository.findAll();

        return tarefas.stream()
                      .map(TarefaDTO::convert)
                      .collect(Collectors.toList());
    }

    public TarefaDTO criarTarefa(TarefaDTO tarefaDTO){

        Tarefa tarefa = tarefaRepository.save(Tarefa.convert(tarefaDTO));

        return TarefaDTO.convert(tarefa);
    }

    public TarefaDTO removerTarefa(long tarefaId){

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                                        .orElseThrow(() -> new RuntimeException("User not found"));

        tarefaRepository.delete(tarefa);
        return TarefaDTO.convert(tarefa);
    }

    public TarefaDTO editarTarefa(long tarefaId, TarefaDTO tarefaDTO){

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                                        .orElseThrow(() -> new RuntimeException("User not found"));

        if( tarefaDTO.getDescricao() != null && !tarefaDTO.getDescricao().equals(tarefa.getDescricao())){
            tarefa.setDescricao(tarefaDTO.getDescricao());

        }

        tarefa = tarefaRepository.save(tarefa);
        return TarefaDTO.convert(tarefa);
    }

}
