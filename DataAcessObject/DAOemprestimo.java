package DataAcessObject;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import database.databaseConnect;

public class DAOemprestimo {

  public boolean registrarEmprestimo(Emprestimo emprestimo, String ra) {
    String sql =
      "INSERT INTO Emprestimo (ra_aluno, codigo_livro, data_emprestimo, data_prevista_devolucao) VALUES (?, ?, ?, ?)";

    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setString(1, ra);
      stmt.setInt(2, emprestimo.getCodigoLivro());
      stmt.setDate(
        3,
        new java.sql.Date(emprestimo.getDataEmprestimo().getTime())
      );
      stmt.setDate(
        4,
        new java.sql.Date(emprestimo.getDataPrevistaDevolucao().getTime())
      );
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public List<Emprestimo> SearchEmprestimoPorRA(String RA) {
    List<Emprestimo> emprestimos = new ArrayList<>();
    String sql = "SELECT * FROM Emprestimo WHERE ra_aluno = ?";

    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setString(1, RA);
      try (ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
          Emprestimo emprestimo = new Emprestimo();
          emprestimo.setId(rs.getInt("id"));
          emprestimo.setDataEmprestimo(rs.getDate("data_emprestimo"));
          emprestimo.setDataPrevistaDevolucao(
            rs.getDate("data_prevista_devolucao")
          );

          Aluno aluno = new DAOaluno().buscarAlunoPorRA(RA);
          emprestimo.setAluno(aluno);

          int codigoLivro = rs.getInt("codigo_livro");
          Livro livro = new DAOlivro().buscarLivroPorCodigo(codigoLivro);
          emprestimo.setLivro(livro);

          emprestimos.add(emprestimo);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return emprestimos;
  }
}
