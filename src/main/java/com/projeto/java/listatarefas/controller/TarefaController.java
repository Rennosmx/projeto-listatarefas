package com.projeto.java.listatarefas.controller;


import com.projeto.java.listatarefas.dto.TarefaDTO;
import com.projeto.java.listatarefas.model.Tarefa;
import com.projeto.java.listatarefas.service.TarefaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController("/tarefa")
@RequiredArgsConstructor
public class TarefaController {

    private final TarefaService tarefaService;

    @GetMapping
    public List<TarefaDTO> listarTarefas(){
        return tarefaService.listarTarefas();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TarefaDTO criarTarefa(@RequestBody @Valid TarefaDTO tarefaDTO){
        return tarefaService.criarTarefa(tarefaDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerTarefa(@PathVariable Long id){
        tarefaService.removerTarefa(id);
    }

    @PutMapping("/{id}")
    public TarefaDTO editarTarefa(@PathVariable Long id, @RequestBody TarefaDTO tarefaDTO){
        return tarefaService.editarTarefa(id, tarefaDTO);
    }

}