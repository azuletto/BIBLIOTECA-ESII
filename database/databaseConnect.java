package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class databaseConnect {

  //configuracoes do bd
  private static final String URL =
    "jdbc:postgresql://localhost:5432/biblioteca";
  private static final String USER = "postgres";
  private static final String PASS = "root";

  public static Connection openConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASS);
  }

  public static void closeConnection(Connection conn) {
    if (conn != null) {
      try {
        conn.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }
}
