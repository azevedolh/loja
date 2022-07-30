package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Pedido;
import br.com.alura.loja.vo.RelatorioDeVendasVo;

public class PedidoDao {

	private EntityManager entityManager;

	public PedidoDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void cadastrar(Pedido pedido) {
		this.entityManager.persist(pedido);
	}

	public Pedido buscarPorId(Long id) {
		return this.entityManager.find(Pedido.class, id);
	}

	public BigDecimal buscarValorTotal() {
		String query = "SELECT SUM(p.valorTotal) FROM Pedido p";
		return this.entityManager.createQuery(query, BigDecimal.class).getSingleResult(); 
	}
	
	public List<RelatorioDeVendasVo> buscarRelatorioDeVendas() {
		String query = "SELECT new br.com.alura.loja.vo.RelatorioDeVendasVo("
				+ "produto.nome, "
				+ "SUM(item.quantidade), "
				+ "MAX(pedido.data)) "
				+ "FROM Pedido pedido "
				+ "JOIN pedido.itens item "
				+ "JOIN item.produto produto "
				+ "GROUP BY produto.nome "
				+ "ORDER BY item.quantidade DESC ";
		return this.entityManager.createQuery(query, RelatorioDeVendasVo.class).getResultList();
	}

}
