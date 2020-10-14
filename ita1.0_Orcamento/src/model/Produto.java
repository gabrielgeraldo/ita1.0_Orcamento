package model;

import java.math.BigDecimal;

public class Produto {

	private String codigo;

	private String descricao;

	private Fornecedor fornecedor;

	private Categoria categoria;

	private BigDecimal precoCusto;

	private BigDecimal precoUnitario;

	private int qtdEstq;

	private String ncm;

	private String unidadeComercial;

	private String csosn;

	private BigDecimal pCredSN;

	private BigDecimal vCredICMSSN;

	private BigDecimal vBCSTRet;

	private BigDecimal vICMSSTRet;

	private BigDecimal pST;

	private String Cest;

	private String codigoBarras;

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Fornecedor getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(Fornecedor fornecedor) {
		this.fornecedor = fornecedor;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoUnitario() {
		return precoUnitario;
	}

	public void setPrecoUnitario(BigDecimal precoUnitario) {
		this.precoUnitario = precoUnitario;
	}

	public int getQtdEstq() {
		return qtdEstq;
	}

	public void setQtdEstq(int qtdEstq) {
		this.qtdEstq = qtdEstq;
	}

	public String getNcm() {
		return ncm;
	}

	public void setNcm(String ncm) {
		this.ncm = ncm;
	}

	public String getUnidadeComercial() {
		return unidadeComercial;
	}

	public void setUnidadeComercial(String unidadeComercial) {
		this.unidadeComercial = unidadeComercial;
	}

	public String getCsosn() {
		return csosn;
	}

	public void setCsosn(String csosn) {
		this.csosn = csosn;
	}

	public BigDecimal getpCredSN() {
		return pCredSN;
	}

	public void setpCredSN(BigDecimal pCredSN) {
		this.pCredSN = pCredSN;
	}

	public BigDecimal getvCredICMSSN() {
		return vCredICMSSN;
	}

	public void setvCredICMSSN(BigDecimal vCredICMSSN) {
		this.vCredICMSSN = vCredICMSSN;
	}

	public BigDecimal getvBCSTRet() {
		return vBCSTRet;
	}

	public BigDecimal getvICMSSTRet() {
		return vICMSSTRet;
	}

	public void setvBCSTRet(BigDecimal vBCSTRet) {
		this.vBCSTRet = vBCSTRet;
	}

	public void setvICMSSTRet(BigDecimal vICMSSTRet) {
		this.vICMSSTRet = vICMSSTRet;
	}

	public BigDecimal getpST() {
		return pST;
	}

	public void setpST(BigDecimal pST) {
		this.pST = pST;
	}

	public String getCest() {
		return Cest;
	}

	public void setCest(String cest) {
		Cest = cest;
	}

	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
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
		Produto other = (Produto) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return descricao;
	}

}
