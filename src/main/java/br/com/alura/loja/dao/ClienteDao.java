package br.com.alura.loja.dao;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Cliente;

public class ClienteDao {
	
	private EntityManager entityManager;

	public ClienteDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public void cadastrar(Cliente cliente) {
		this.entityManager.persist(cliente);
	}

	public Cliente buscarPorId(Long id) {
		return this.entityManager.find(Cliente.class, id);
	}

}
