package controller;

import java.util.ArrayList;
import java.util.List;

import model.Aluno;
import model.Livro;

public class Controle {

  public boolean Emprestar(String RA, String aluno, int[] codigos, int num) {
    boolean retorno = true;

    Aluno a = new Aluno(RA, aluno);

    if (!a.verificarAlunoCadastrado(RA)) {
      System.out.println("Aluno Inexistente");
      retorno = false;
    }

    if (!a.verificarDebitosAluno(RA)) {
      System.out.println("Aluno em Debito");
      retorno = false;
    }

    if (retorno) {
      List<Livro> livros = new ArrayList<Livro>();

      for (int i = 0; i < num; i++) {
        Livro l = new Livro(codigos[i], null);

        if (!l.verificaLivro()) livros.add(l);
      }

      if (livros.size() > 0) {
        retorno = a.Emprestar(livros);
        return retorno;
      } else return false;
    } else return retorno;
  }
}
