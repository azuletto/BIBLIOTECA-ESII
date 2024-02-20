package model;

import java.util.Date;

public class Devolucao {

  String raAluno;
  int codigoLivro;
  Date dataDevolucao;

  public Devolucao(String raAluno, int codigoLivro, Date dataDevolucao) {
    this.raAluno = raAluno;
    this.codigoLivro = codigoLivro;
    this.dataDevolucao = dataDevolucao;
  }

  public String getRaAluno() {
    return raAluno;
  }

  public int getCodigoLivro() {
    return codigoLivro;
  }

  public Date getDataDevolucao() {
    return dataDevolucao;
  }
}
