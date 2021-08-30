package com.devsuperior.dsvendas.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Repository;

import com.devsuperior.dsvendas.entities.Teste;

@Repository
public interface TesteRepository extends JpaRepository<Teste, Long>, TesteRepositoryQueries {

	List<Teste> findTesteByNome(String nome);

	List<Teste> findTesteByNomeContaining(String nome);

	Streamable<Teste> queryTesteByNomeContaining(String nome);

//	@Query("from Teste t where t.nome = :nome")
	Page<Teste> consultarByNome(String nome, Pageable pageable);

}
