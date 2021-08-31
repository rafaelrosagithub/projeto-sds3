package com.devsuperior.dsvendas.repositories;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.devsuperior.dsvendas.entities.Teste;

public interface TesteRepositoryQueries {

	List<Teste> find(String nome, String visited, Pageable pageable);

}