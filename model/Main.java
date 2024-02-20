package model;

import java.util.Scanner;

import controller.Controle;

public class Main {

  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);
    int[] codigos = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
    System.out.print("Digite o RA do aluno: ");
    String aluno = entrada.nextLine();
    System.out.print("Digite o numero de livros a ser emprestado: ");
    int num = entrada.nextInt();
    int aux;
    for (int i = 0; i < num; i++) {
      System.out.print("Digite o codigo do livro " + (i + 1) + ": ");
      aux = entrada.nextInt();
      codigos[i] = aux;
    }

    Controle c = new Controle();
    c.Emprestar(aluno, null, codigos, num);
  }
}
