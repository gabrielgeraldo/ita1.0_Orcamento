package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Orcamento {

	private Long codigo;

	private Cliente cliente;

	private Date data = new Date();

	private BigDecimal total;

	private Usuario usuario;

	private List<ItemOrcamento> itens = new ArrayList<>();

	public Long getCodigo() {
		return codigo;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public Date getData() {
		return data;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<ItemOrcamento> getItens() {
		return itens;
	}

	public void setItens(List<ItemOrcamento> itens) {
		this.itens = itens;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orcamento other = (Orcamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Orcamento [codigo=" + codigo + "]";
	}

}
