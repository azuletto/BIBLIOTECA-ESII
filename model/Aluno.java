package model;

import java.util.List;

import DataAcessObject.DAOaluno;

public class Aluno {
String RA;
String nome;
boolean debito;

	public Aluno(String RA, String nome) {
		super();
		this.RA = RA;
		this.nome = nome;
		
	}

	public String getNome() {
		return nome;
	}

	public String getRA() {
		return RA;
	}

	public void setNome(String nome) {
		this.RA = nome;
	}

	public void setRA(String RA) {
		this.RA = RA;
	}

	public void setDebito(boolean debito) {
        this.debito = debito;
    }

	public boolean verificarAlunoCadastrado(String ra) {
		return DAOaluno.verificarAlunoCadastrado(ra);
	}

	public boolean verificarDebitosAluno(String ra) {
		return DAOaluno.verificarDebitosAluno(ra);
	}

	//Metodo que delega a funcionalidade de emprestar para a classe emprestimo
	public boolean Emprestar(List<Livro> livros){   
		/* Aqui voc  deve intanciar um objeto emprestimo */
		/* Aqui voc  deve chamar o metodo emprestar da classe empresitmo*/
		Emprestimo emprestimo = new Emprestimo();
		return emprestimo.emprestar(livros, this.RA);
		
	}
}
