package DataAcessObject;

import model.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.databaseConnect;

public class DAOlivro {

  public static boolean cadastrarLivro(String titulo, int id) {
    String sql = "INSERT INTO livros (titulo, id, disponivel) VALUES (?, ?, ?)";

    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setString(1, titulo);
      stmt.setInt(2, id);
      stmt.setBoolean(3, true);
      stmt.executeUpdate();
      return true;
    } catch (SQLException e) {
      e.printStackTrace();
      return false;
    }
  }

  public static void marcarLivroComoDisponivel(int codigoLivro)
    throws SQLException {
    String sql =
      "UPDATE livros SET disponivel = ?, prazo_emprestimo = NULL WHERE id = ?";
    try (
      Connection connection = databaseConnect.openConnection();
      PreparedStatement statement = connection.prepareStatement(sql)
    ) {
      statement.setBoolean(1, true);
      statement.setInt(2, codigoLivro);
      statement.executeUpdate();
    }
  }

  public Livro buscarLivroPorCodigo(int codigo) {
    Livro livro = null;
    String sql = "SELECT * FROM livros WHERE id = ?";

    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setInt(1, codigo);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          String titulo = rs.getString("titulo");
          livro = new Livro(codigo, titulo);
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return livro;
  }

  public boolean verificaDisponibilidade(int codigoLivro) {
    boolean disponivel = false;
    String sql = "SELECT disponivel FROM livros WHERE codigo = ?";

    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setInt(1, codigoLivro);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          disponivel = rs.getBoolean("disponivel");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return disponivel;
  }

  public Date getDataDevolucao(int codigoLivro) {
    Date dataDevolucao = null;
    String sql =
      "SELECT data_prevista_devolucao FROM Emprestimo WHERE codigo_livro = ?";

    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setInt(1, codigoLivro);
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          dataDevolucao = rs.getDate("data_prevista_devolucao");
        }
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return dataDevolucao;
  }

  public static boolean verificarIdExistente(int id) {
    String sql = "SELECT COUNT(*) FROM livros WHERE id = ?";
    try (
      Connection conn = databaseConnect.openConnection();
      PreparedStatement stmt = conn.prepareStatement(sql)
    ) {
      stmt.setInt(1, id);
      ResultSet resultSet = stmt.executeQuery();
      if (resultSet.next()) {
        int count = resultSet.getInt(1);
        return count > 0;
      }
    } catch (SQLException ex) {
      ex.printStackTrace();
    }
    return false;
  }
}
