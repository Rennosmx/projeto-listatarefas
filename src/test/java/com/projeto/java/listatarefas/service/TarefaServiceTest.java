package com.projeto.java.listatarefas.service;

import com.projeto.java.listatarefas.dto.TarefaDTO;
import com.projeto.java.listatarefas.model.Tarefa;
import com.projeto.java.listatarefas.repository.TarefaRepository;
import com.projeto.java.listatarefas.service.TarefaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class TarefaServiceTest {

    @InjectMocks
    private TarefaService tarefaService;

    @Mock
    private TarefaRepository tarefaRepository;

    @Test
    void testListarTarefas(){

        List<Tarefa> tarefas = new ArrayList<Tarefa>();
        tarefas.add(new Tarefa(1, "Tarefa 1"));
        tarefas.add(new Tarefa(2, "Tarefa 2"));

        Mockito.when(tarefaRepository.findAll()).thenReturn(tarefas);

        List<TarefaDTO> tarefasDTO = tarefaService.listarTarefas();

        Assertions.assertEquals(2, tarefasDTO.size());
    }

    @Test
    void testCriarTarefa(){

        Tarefa tarefaDB = new Tarefa(1, "Tarefa 1");
        //TarefaDTO tarefaDTO = TarefaDTO.convert(tarefaDB);

        Mockito.when(tarefaRepository.save(Mockito.any())).thenReturn(tarefaDB);

        TarefaDTO tarefasDTO = tarefaService.criarTarefa(TarefaDTO.convert(tarefaDB));

        Assertions.assertEquals("Tarefa 1", tarefasDTO.getDescricao());
    }

    @Test
    void testEditarTarefa(){

        Tarefa tarefaDB = new Tarefa(1, "Tarefa 1");

        Mockito.when(tarefaRepository.findById(tarefaDB.getId())).thenReturn(Optional.of(tarefaDB));
        Mockito.when(tarefaRepository.save(Mockito.any())).thenReturn(tarefaDB);

        tarefaDB.setDescricao("Nova Descrição Tarefa 1");

        TarefaDTO tarefasDTO = tarefaService.editarTarefa(tarefaDB.getId(), TarefaDTO.convert(tarefaDB));

        Assertions.assertEquals("Nova Descrição Tarefa 1", tarefasDTO.getDescricao());
    }

}
