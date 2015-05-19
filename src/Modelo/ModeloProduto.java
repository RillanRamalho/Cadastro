package Modelo;

public class ModeloProduto {
	private String NomeProduto;
	private double preçoProduto;
	private int qntProduto;
	private String fornecedor;
	private int idProduto;
	public int getIdProduto() {
		return idProduto;
	}
	public void setIdProduto(int idProduto) {
		this.idProduto = idProduto;
	}
	public String getNomeProduto() {
		return NomeProduto;
	}
	public void setNomeProduto(String nomeProduto) {
		NomeProduto = nomeProduto;
	}
	public double getPreçoProduto() {
		return preçoProduto;
	}
	public void setPreçoProduto(double preçoProduto) {
		this.preçoProduto = preçoProduto;
	}
	public int getQntProduto() {
		return qntProduto;
	}
	public void setQntProduto(int qntProduto) {
		this.qntProduto = qntProduto;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}


}
