package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ClienteDao;
import br.com.alura.loja.dao.PedidoDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Cliente;
import br.com.alura.loja.modelo.ItemPedido;
import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class TesteCriteria {

	public static void main(String[] args) {
		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();

		em.getTransaction().begin();
		ProdutoDao produtoDao = new ProdutoDao(em);
		Produto produto = produtoDao.buscarPorIdComCategoria(1l);
		List<Produto> produto2 = produtoDao.buscarPorParametrosJpql(null, null, LocalDate.now());
		List<Produto> produto3 = produtoDao.buscarPorParametrosCriteria("iphone", null, null);

		em.getTransaction().commit();
		em.close();
		
		System.out.println(produto.getCategoria().toString());
		produto3.stream().forEach(p -> System.out.println(p.toString()));
		System.out.println("A lista está vazia: " + produto3.isEmpty());
	}

	private static void popularBancoDeDados() {
		Categoria categoria = new Categoria("CELULARES");
		Produto produto = new Produto("Xiaomi", "Xiaomi Redmi Note 9S", new BigDecimal("800"), categoria);
		Produto produto2 = new Produto("Iphone", "Iphone 13", new BigDecimal("10000"), categoria);
		Cliente cliente = new Cliente("Maria", "12345678909");
		Pedido pedido = new Pedido(cliente);
		ItemPedido itemPedido1 = new ItemPedido(2, produto, pedido);
		ItemPedido itemPedido2 = new ItemPedido(1, produto2, pedido);
		pedido.adicionarItem(itemPedido1);
		pedido.adicionarItem(itemPedido2);

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();

		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(produto);
		produtoDao.cadastrar(produto2);
		clienteDao.cadastrar(cliente);
		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		em.close();
	}

}
