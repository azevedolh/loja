package br.com.alura.loja.modelo;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itens_pedido")
public class ItemPedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "preco_unitario")
	private BigDecimal precoUnitario;
	private int quantidade;

	@ManyToOne(fetch = FetchType.LAZY)
	private Produto produto;

	@ManyToOne(fetch = FetchType.LAZY)
	private Pedido pedido;

	public ItemPedido() {
	}

	public ItemPedido(int quantidade, Produto produto, Pedido pedido) {
		this.quantidade = quantidade;
		this.produto = produto;
		this.precoUnitario = produto.getPreco();
		this.pedido = pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}
	
	public BigDecimal getValorTotalItem() {
		return this.precoUnitario.multiply(new BigDecimal(quantidade));
	}

	@Override
	public String toString() {
		return "ItemPedido [id=" + id + ", precoUnitario=" + precoUnitario + ", quantidade=" + quantidade + ", produto="
				+ produto + ", pedido=" + pedido + "]";
	}

}
