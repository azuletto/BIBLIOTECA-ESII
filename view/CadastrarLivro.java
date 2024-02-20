package view;

import database.databaseConnect;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import DataAcessObject.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.*;

public class CadastrarLivro extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setMaximized(true);
    primaryStage.setTitle("Cadastro de livro");

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.BOTTOM_LEFT);
    gridPane.setHgap(20);
    gridPane.setVgap(10);

    Label lblTitulo = new Label("Título:");
    TextField txtTitulo = new TextField();
    gridPane.add(lblTitulo, 0, 0);
    gridPane.add(txtTitulo, 1, 0);

    Label lblId = new Label("ID:");
    TextField txtId = new TextField();
    gridPane.add(lblId, 0, 1);
    gridPane.add(txtId, 1, 1);

    Button btnCadastrar = new Button("Cadastrar");
    Button btnVoltarInicio = new Button("Voltar ao Início");

    btnCadastrar.setOnAction(e -> {
      String titulo = txtTitulo.getText();
      int id = Integer.parseInt(txtId.getText());

      if (DAOlivro.verificarIdExistente(id)) {
        exibirAlerta(Alert.AlertType.ERROR, "Erro", "ID já existente.");
      } else {
        boolean cadastrou = DAOlivro.cadastrarLivro(titulo, id);
        if (cadastrou) {
          exibirAlerta(
            Alert.AlertType.INFORMATION,
            "Sucesso",
            "Livro cadastrado com sucesso!"
          );
        } else {
          exibirAlerta(
            Alert.AlertType.ERROR,
            "Erro",
            "Ocorreu um erro ao cadastrar o livro."
          );
        }
      }
    });

    btnVoltarInicio.setOnAction(e -> primaryStage.close());

    VBox vbox = new VBox(10);
    vbox.setAlignment(Pos.BOTTOM_LEFT);
    vbox.getChildren().addAll(gridPane, btnCadastrar, btnVoltarInicio);

    Image icon = new Image("view\\img\\pngtree-vector-book-icon-png-image_995152.jpg");
    primaryStage.getIcons().add(icon);

    BackgroundImage myBI= new BackgroundImage(new Image("view\\img\\4823754_d508394098f8f23.jpg",500,500,false,true),
    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    BackgroundSize.DEFAULT);
    vbox.setBackground(new Background(myBI));

    Scene scene = new Scene(vbox, 350, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static void exibirAlerta(
    Alert.AlertType tipo,
    String titulo,
    String mensagem
  ) {
    Alert alerta = new Alert(tipo);
    alerta.setTitle(titulo);
    alerta.setHeaderText(null);
    alerta.setContentText(mensagem);
    alerta.showAndWait();
  }
}
