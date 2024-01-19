package com.projeto.java.listatarefas.service;

import com.projeto.java.listatarefas.dto.TarefaDTO;
import com.projeto.java.listatarefas.model.Tarefa;
import com.projeto.java.listatarefas.repository.TarefaRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TarefaService {


    private TarefaRepository tarefaRepository;

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
                                        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        tarefaRepository.delete(tarefa);
        return TarefaDTO.convert(tarefa);
    }

    public TarefaDTO editarTarefa(long tarefaId, TarefaDTO tarefaDTO){

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                                        .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        if( tarefaDTO.getDescricao() != null && !tarefaDTO.getDescricao().equals(tarefa.getDescricao())){
            tarefa.setDescricao(tarefaDTO.getDescricao());
        }

        tarefa = tarefaRepository.save(tarefa);
        return TarefaDTO.convert(tarefa);
    }

}
