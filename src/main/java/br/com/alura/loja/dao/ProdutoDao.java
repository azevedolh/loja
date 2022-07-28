package br.com.alura.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;

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
		String query = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return this.entityManager.createQuery(query, Produto.class)
				.setParameter("nome", nome)
				.getResultList();
	}

}
