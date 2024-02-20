package model;

import DataAcessObject.*;

public class Debito {

  int codigoAluno;

  public Debito(int aluno) {
    this.codigoAluno = aluno;
  }

  public boolean verificaDebito(String ra) {
    return DAOaluno.verificarDebitosAluno(ra);
  }
}
