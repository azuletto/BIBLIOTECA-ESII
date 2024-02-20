package model;

import java.util.Calendar;
import java.util.Date;

public class Item {

  //Cada Item tem um livro associado
  Livro livro;
  Date dataDevolucao;

  //Quando um item   criado, um livro   associado a ele
  public Item(Livro livro) {
    super();
    this.livro = livro;
  }

  public Date getDataDevolucao() {
    return dataDevolucao;
  }

  public void setDataDevolucao(Date dataDevolucao) {
    this.dataDevolucao = dataDevolucao;
  }

  public Livro getLivro() {
    return livro;
  }

  public void setLivro(Livro livro) {
    this.livro = livro;
  }

  //Metodo para calcular a data de Devolucao de cada Item
  public Date calculaDataDevolucao(Date data) {
    dataDevolucao = data;
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(data);
    calendar.add(Calendar.DATE, livro.verPrazo());
    dataDevolucao = calendar.getTime();
    return dataDevolucao;
  }
}
