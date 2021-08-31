package com.devsuperior.dsvendas.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.devsuperior.dsvendas.entities.Teste;
import com.devsuperior.dsvendas.repositories.TesteRepositoryQueries;

@Repository
public class TesteRepositoryImpl implements TesteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Teste> find(String nome, String visited, Pageable pageable) {
		

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("SELECT * FROM tb_teste etr1 ");
		stringBuilder.append("join tb_teste etr2 on etr2.id = etr1.id ");
		stringBuilder.append("where etr1.deals in (select deals from tb_teste etr group by deals) ");
		stringBuilder.append("and etr1.date in (select MAX(etr.date) from tb_teste etr group by deals)");
			
							
		
		Query query =  manager.createNativeQuery(stringBuilder.toString(), Teste.class);
		
		List<Teste> lista = query.getResultList();
		lista.stream().forEach(arr -> System.out.println("Teste: " + arr.getId() + " - " +  arr.getNome()));
		return lista;

//		var jpql = new StringBuilder();
//
//		jpql.append("from Teste where 0 = 0 ");
//
//		var parametros = new HashMap<String, Object>();
//
//		if (StringUtils.hasLength(nome)) {
//			jpql.append("and nome like :nome ");
//			parametros.put("nome", "%" + nome + "%");
//		}
//
//		if (StringUtils.hasLength(visited)) {
//			jpql.append("and visited >= :visited ");
//			parametros.put("visited", visited);
//		}
//
//		TypedQuery<Teste> query = manager.createQuery(jpql.toString(), Teste.class);
//		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//		
//		return query.getResultList();
		
//		CriteriaBuilder builder = manager.getCriteriaBuilder();
//		
//		CriteriaQuery<Teste> criteria = builder.createQuery(Teste.class);
//		Root<Teste> root = criteria.from(Teste.class);
//		
//		var predicates = new ArrayList<Predicate>();
//		
//		if (StringUtils.hasText(nome)) {
//			predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
//		}
//		
//		if (StringUtils.hasLength(visited)) {
//			predicates.add(builder.greaterThanOrEqualTo(root.get("visited"), visited));
//		}
//		
//		criteria.where(predicates.toArray(new Predicate[0]));
//		
//		
//		TypedQuery<Teste> query = manager.createQuery(criteria);
//		
//		return query.getResultList();
	}

}
