package com.devsuperior.dsvendas.controllers;

import java.util.List;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.dsvendas.entities.Teste;
import com.devsuperior.dsvendas.repositories.TesteRepository;

@RestController
@RequestMapping("/teste")
public class TesteController {

	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	private TesteRepository testeRepository;

	@GetMapping("/por-nome")
	public Stream<Teste> salePorNome(@RequestParam("nomeParam") String nome) {
		

		return testeRepository.queryTesteByNomeContaining(nome).stream().distinct();
	}
	
	@GetMapping
	public ResponseEntity<Page<Teste>> findAll(Pageable pageable) {
		Page<Teste> list = testeRepository.findAll(pageable);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/consultar")
	public ResponseEntity<Page<Teste>> consultarByNome(String nome, Pageable pageable) {
		Page<Teste> list = testeRepository.consultarByNome(nome, pageable);
		return ResponseEntity.ok(list);
	}
	
	@GetMapping("/consultar-repository-impl")
	public ResponseEntity<List<Teste>> consultarByRepositoryImpl(String nome, String visited) {
		List<Teste> list = testeRepository.find(nome, visited);
		return ResponseEntity.ok(list);
	}

}
