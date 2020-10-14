package model;

import com.fincatto.documentofiscal.nfe400.classes.nota.NFIndicadorIEDestinatario;

public class Fornecedor {

	private Long codigo;

	private String tipo;

	private String cgc;

	private String inscricaoEstadual;

	private String razaoSocial;

	private String nomeFantasia;

	private String telefone;

	private String celular;

	private String email;

	private String obs;

	private String endereco;

	private String numero;

	private Estado estado;

	private Municipio municipio;

	private String bairro;

	private String cep;

	private String complemento;

	private NFIndicadorIEDestinatario nfIndicadorIEDestinatario;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getCgc() {
		return cgc;
	}

	public void setCgc(String cgc) {
		this.cgc = cgc.replaceAll("\\.", "").replaceAll("\\/", "").replace("-", "").replace("(", "").replace(")", "");
	}

	public String getInscricaoEstadual() {
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual) {
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone.replaceAll("\\.", "").replaceAll("\\/", "").replace("-", "").replace("(", "")
				.replace(")", "");
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular.replaceAll("\\.", "").replaceAll("\\/", "").replace("-", "").replace("(", "")
				.replace(")", "");
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getObs() {
		return obs;
	}

	public void setObs(String obs) {
		this.obs = obs;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep.replaceAll("\\.", "").replaceAll("\\/", "").replace("-", "").replace("(", "").replace(")", "");
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public NFIndicadorIEDestinatario getNfIndicadorIEDestinatario() {
		return nfIndicadorIEDestinatario;
	}

	public void setNfIndicadorIEDestinatario(NFIndicadorIEDestinatario nfIndicadorIEDestinatario) {
		this.nfIndicadorIEDestinatario = nfIndicadorIEDestinatario;
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
		Fornecedor other = (Fornecedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return razaoSocial;
	}

}