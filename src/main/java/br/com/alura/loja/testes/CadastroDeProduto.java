package br.com.alura.loja.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alura.loja.modelo.Produto;

public class CadastroDeProduto {

	public static void main(String[] args) {
		Produto produto = new Produto();
		produto.setNome("Xiaomi");
		produto.setDescricao("Xiaomi Redmi Note 9S");
		produto.setPreco(new BigDecimal("800"));
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("loja");
		EntityManager em = factory.createEntityManager();
		
		em.getTransaction().begin();
		em.persist(produto); 
		em.getTransaction().commit();
		em.close();

	}

}
