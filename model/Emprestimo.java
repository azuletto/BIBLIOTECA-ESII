package model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import DataAcessObject.*;

public class Emprestimo {
    private Date dataEmprestimo = new Date();
    private Date dataPrevista = new Date();
    private List<Item> itens = new ArrayList<>();
    private Aluno aluno;
    private Livro livro;
	private int id;

    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }

    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }

    public Date getDataPrevistaDevolucao() {
        return dataPrevista;
    }

    public void setDataPrevistaDevolucao(Date dataPrevista) {
        this.dataPrevista = dataPrevista;
    }

    public String getRAAluno() {
        return aluno.getRA();
    }

    public int getCodigoLivro() {
        return livro.getCodigo();
    }

	public void setId(int id) {
        this.id = id;
    }

	public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public boolean criarEmprestimo(String ra) {
        DAOemprestimo emprestimoDAO = new DAOemprestimo();
        return emprestimoDAO.registrarEmprestimo(this, ra);
    }

    public boolean emprestar(List<Livro> livros, String ra) {
        DAOaluno alunoDAO = new DAOaluno();
        this.dataEmprestimo = new Date();
        this.aluno = alunoDAO.buscarAlunoPorRA(ra); // Obtém o aluno do banco de dados usando AlunoDAO
        this.itens.clear();
    
        DAOlivro livroDAO = new DAOlivro(); // Instanciando LivroDAO
    
        for (Livro livro : livros) {
            if (!livro.verificaLivro()) {
                // Livro não pode ser emprestado
                System.out.println("O livro " + livro.getTitulo() + " não pode ser emprestado.");
            } else if (livroDAO.verificaDisponibilidade(livro.getCodigo())) {
                // Livro está reservado
                Date dataDevolucao = livroDAO.getDataDevolucao(livro.getCodigo());
                System.out.println("O livro " + livro.getTitulo() + " está reservado até " + dataDevolucao);
            } else {
                // Livro pode ser emprestado
                // Crie um item de empréstimo associado ao livro
                Item item = new Item(livro);
                item.setLivro(livro);
                // Adicione o item de empréstimo ao empréstimo
                this.itens.add(item);
            }
        }
    
        // Calcula a data de devolução
        calcularDataDevolucao();
    
        DAOemprestimo emprestimoDAO = new DAOemprestimo();
        // Realiza o empréstimo no banco de dados
        return emprestimoDAO.registrarEmprestimo(this, ra);
    }
    

    private void calcularDataDevolucao() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.dataPrevista);

        // Adiciona 14 dias à data atual
        calendar.add(Calendar.DAY_OF_YEAR, 14);
        this.dataPrevista = calendar.getTime();
    }
}
