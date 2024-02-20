package model;

public class Titulo {

  int prazo;
  int codigo;
  String titulo;

  public Titulo(int codigo, String titulo) {
    this.codigo = codigo;
    this.prazo = codigo + 1;
    this.titulo = titulo;
  }

  public int getPrazo() {
    return prazo;
  }

  public void setPrazo(int prazo) {
    this.prazo = prazo;
  }

  public String getTitulo() {
    return titulo;
  }

  public void setTitulo(String titulo) {
    this.titulo = titulo;
  }

  public int getCodigo() {
    return codigo;
  }

  public void setCodigo(int codigo) {
    this.codigo = codigo;
  }
}
