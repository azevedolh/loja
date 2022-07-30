package br.com.alura.loja.testes;

import java.math.BigDecimal;
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
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class CadastroDeCliente {

	public static void main(String[] args) {
		popularBancoDeDados();

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		ClienteDao clienteDao = new ClienteDao(em);
		PedidoDao pedidoDao = new PedidoDao(em);

		em.getTransaction().begin();
		Produto produto1 = produtoDao.buscarPorId(1l);
		Produto produto2 = produtoDao.buscarPorId(2l);
		Cliente cliente = clienteDao.buscarPorId(1l);

		Pedido pedido = new Pedido(cliente);
		ItemPedido itemPedido1 = new ItemPedido(2, produto1, pedido);
		ItemPedido itemPedido2 = new ItemPedido(1, produto2, pedido);
		pedido.adicionarItem(itemPedido1);
		pedido.adicionarItem(itemPedido2);

		pedidoDao.cadastrar(pedido);

		em.getTransaction().commit();
		
		BigDecimal valorTotal = pedidoDao.buscarValorTotal();
		System.out.println("Valor total dos pedidos: " + valorTotal);
		
		List<RelatorioDeVendasVo> relatorio = pedidoDao.buscarRelatorioDeVendas();
		
		for (RelatorioDeVendasVo linha : relatorio) {
			System.out.println(linha.toString());
		}
		
		em.close();

	}

	private static void popularBancoDeDados() {
		Categoria categoria = new Categoria("CELULARES");
		Produto produto = new Produto("Xiaomi", "Xiaomi Redmi Note 9S", new BigDecimal("800"), categoria);
		Produto produto2 = new Produto("Iphone", "Iphone 13", new BigDecimal("10000"), categoria);
		Cliente cliente = new Cliente("Maria", "12345678909");

		EntityManager em = JPAUtil.getEntityManager();

		ProdutoDao produtoDao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);
		ClienteDao clienteDao = new ClienteDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(categoria);
		produtoDao.cadastrar(produto);
		produtoDao.cadastrar(produto2);
		clienteDao.cadastrar(cliente);
		em.getTransaction().commit();
		em.close();
	}

}
