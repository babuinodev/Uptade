package entidade;

public class Carro {
	 private int id;
	    private String nome;
	    private String modelo;
	    private int ano;
	    private double preco;

	    public Carro(int id, String nomedocarro, String modelo, int ano, double preco) {
	        this.id = id;
	        this.nome = nomedocarro;
	        this.modelo = modelo;
	        this.ano = ano;
	        this.preco = preco;
	    }

	    // Getters e Setters
	    public int getid() { return id; }
	    public String getNomedocarro() { return nome; }
	    public String getModelo() { return modelo; }
	    public int getAno() { return ano; }
	    public double getPreco() { return preco; }
	}


