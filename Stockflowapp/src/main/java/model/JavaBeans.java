package model;

public class JavaBeans {
	private String idcon;
	private String nome;
	private String descricao;
	private String valor;
	private String tamanho;
	private String cor;
	public String getIdcon() {
		return idcon;
	}
	public void setIdcon(String idcon) {
		this.idcon = idcon;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public String getTamanho() {
		return tamanho;
	}
	public void setTamanho(String tamanho) {
		this.tamanho = tamanho;
	}
	public String getCor() {
		return cor;
	}
	public void setCor(String cor) {
		this.cor = cor;
	}
	public JavaBeans(String idcon, String nome, String descricao, String valor, String tamanho, String cor) {
		super();
		this.idcon = idcon;
		this.nome = nome;
		this.descricao = descricao;
		this.valor = valor;
		this.tamanho = tamanho;
		this.cor = cor;
	}
	public JavaBeans() {
		super();
	}
	
	
}
