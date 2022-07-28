package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {
		cadastrarProduto();

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtoDao = new ProdutoDao(em);

		System.out.println("BUSCAR POR ID");
		Produto produto = produtoDao.buscarPorId(1l);
		System.out.println(produto.toString());
		
		System.out.println("BUSCAR TODOS");
		List<Produto> produtos = produtoDao.buscarTodos();
		produtos.stream().forEach(p -> System.out.println(p.toString()));
		
		System.out.println("BUSCAR POR NOME");
		List<Produto> produtosPorNome = produtoDao.buscarPorNome("Iphone");
		produtosPorNome.stream().forEach(p -> System.out.println(p.toString()));
		
		System.out.println("BUSCAR POR NOME DA CATEGORIA");
		List<Produto> produtosPorCategoria = produtoDao.buscarPorCategoria("CELULARES");
		produtosPorCategoria.stream().forEach(p -> System.out.println(p.toString()));
	}

	private static void cadastrarProduto() {
		Categoria categoria = new Categoria("CELULARES");
		Produto produto  = new Produto("Xiaomi", "Xiaomi Redmi Note 9S", new BigDecimal("800"), categoria);
		Produto produto2 = new Produto("Iphone", "Iphone 13", new BigDecimal("10000"), categoria);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(produto);
		produtoDao.cadastrar(produto2);
		em.getTransaction().commit();
		em.close();
	}

}
