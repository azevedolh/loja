package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {
	
	private EntityManager entityManager;

	public ProdutoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Produto produto) {
		this.entityManager.persist(produto);
	}

	public Produto buscarPorId(Long id) {
		return this.entityManager.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String query = "SELECT p FROM Produto p";
		return this.entityManager.createQuery(query, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String nome) {
		String query = "SELECT p FROM Produto p WHERE p.nome = :nome OR p.nome = ?1";
		return this.entityManager.createQuery(query, Produto.class)
				.setParameter("nome", nome)
				.setParameter(1, nome)
				.getResultList();
	}

	public List<Produto> buscarPorCategoria(String nome) {
		return this.entityManager.createNamedQuery("Produto.consultaPorCategoria", Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}

	public Produto buscarPorIdComCategoria(long id) {
		String query = "SELECT p FROM Produto p JOIN FETCH p.categoria WHERE p.id = :id";
		return this.entityManager.createQuery(query, Produto.class)
				.setParameter("id", id)
				.getSingleResult();
	}
	
	public List<Produto> buscarPorParametrosJpql(String nome, BigDecimal preco, LocalDate dataCadastro) {
		String queryString = "SELECT p FROM Produto p WHERE 1=1 ";
		
		if (nome != null && !nome.trim().isEmpty()) {
			queryString += "AND p.nome = :nome ";
		}
		if (preco != null) {
			queryString += "AND p.preco = :preco ";
		}
		if (dataCadastro != null) {
			queryString += "AND p.dataCadastro = :dataCadastro ";
		}
		
		TypedQuery<Produto> query = this.entityManager.createQuery(queryString, Produto.class);
		
		if (nome != null && !nome.trim().isEmpty()) {
			query.setParameter("nome", nome);
		}
		if (preco != null) {
			query.setParameter("preco", preco);
		}
		if (dataCadastro != null) {
			query.setParameter("dataCadastro", dataCadastro);
		}
		
		return query.getResultList();
	}
	
	public List<Produto> buscarPorParametrosCriteria(String nome, BigDecimal preco, LocalDate dataCadastro) {
		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		CriteriaQuery<Produto> query = builder.createQuery(Produto.class);
		Root<Produto> from = query.from(Produto.class);
		
		Predicate filtros = builder.and();
		
		if (nome != null && !nome.trim().isEmpty()) {
			filtros = builder.and(filtros, builder.equal(from.get("nome"), nome));
		}
		if (preco != null) {
			filtros = builder.and(filtros, builder.equal(from.get("preco"), preco));
		}
		if (dataCadastro != null) {
			filtros = builder.and(filtros, builder.equal(from.get("dataCadastro"), dataCadastro));
		}
		
		query.where(filtros);
		
		return this.entityManager.createQuery(query).getResultList();
	}

}
