package br.com.alura.loja.vo;

import java.time.LocalDate;

public class RelatorioDeVendasVo {

	private String nomeProduto;
	private Long quantidadeTotal;
	private LocalDate dataUltimaCompra;

	public RelatorioDeVendasVo(String nomeProduto, Long quantidadeTotal, LocalDate dataUltimaCompra) {
		this.nomeProduto = nomeProduto;
		this.quantidadeTotal = quantidadeTotal;
		this.dataUltimaCompra = dataUltimaCompra;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public Long getQuantidadeTotal() {
		return quantidadeTotal;
	}

	public LocalDate getDataUltimaCompra() {
		return dataUltimaCompra;
	}

	@Override
	public String toString() {
		return "RelatorioDeVendasVo [nomeProduto=" + nomeProduto + ", quantidadeTotal=" + quantidadeTotal
				+ ", dataUltimaCompra=" + dataUltimaCompra + "]";
	}

}
