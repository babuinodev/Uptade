package entidade;

public class Funcionario {
	  private int id;
	    private String nome;
	    private double salario;

	    public Funcionario(int id, String nome, double salario) {
	        this.id = id;
	        this.nome = nome;
	        this.salario = salario;
	    }

	    // Getters e Setters
	    public int getid() { return id; }
	    public String getnome() { return nome; }
	    public double getsalario() { return salario; }
	}

