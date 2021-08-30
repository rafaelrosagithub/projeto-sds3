package com.devsuperior.dsvendas.repositories;

import java.util.List;

import com.devsuperior.dsvendas.entities.Teste;

public interface TesteRepositoryQueries {

	List<Teste> find(String nome, String visited);

}