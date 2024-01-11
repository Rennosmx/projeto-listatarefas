package com.projeto.java.listatarefas.repository;

import com.projeto.java.listatarefas.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {



}
