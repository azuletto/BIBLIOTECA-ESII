    package model;

public class Livro {

  int codigo;
  String titulo;
  boolean exemplarBiblioteca;
  Titulo tituloObj;

  public Livro(int codigo, String titulo) {
    this.codigo = codigo;
    this.titulo = titulo;
    this.exemplarBiblioteca = (codigo == 2 || codigo == 4);
    this.tituloObj = new Titulo(codigo, titulo);
  }

  public int getCodigo() {
    return codigo;
  }

  public String getTitulo() {
    return titulo;
  }

  public int verPrazo() {
    return tituloObj.getPrazo();
  }

  public boolean verificaLivro() {
    return exemplarBiblioteca;
  }
}
