package view;

import database.databaseConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import DataAcessObject.DAOaluno;
import DataAcessObject.DAOlivro;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Aluno;

public class CadastrarAluno extends Application {

  @Override
  public void start(Stage primaryStage) {
    primaryStage.setMaximized(true);
    primaryStage.setTitle("Gerenciador de biblioteca");

    GridPane gridPane = new GridPane();
    gridPane.setAlignment(Pos.BOTTOM_LEFT);
    gridPane.setHgap(10);
    gridPane.setVgap(10);

    Label lblNome = new Label("Nome:");
    TextField txtNome = new TextField();
    gridPane.add(lblNome, 0, 0);
    gridPane.add(txtNome, 1, 0);

    Label lblRA = new Label("RA:");
    TextField txtRA = new TextField();
    gridPane.add(lblRA, 0, 1);
    gridPane.add(txtRA, 1, 1);

    Button btnCadastrar = new Button("Cadastrar");
    Button btnVoltarInicio = new Button("Voltar ao Início");

    btnCadastrar.setOnAction(e -> {
      String nome = txtNome.getText();
      String ra = txtRA.getText();

      if (DAOaluno.verificarAlunoCadastrado(ra)) {
        exibirAlerta(Alert.AlertType.ERROR, "Erro", "RA já existente.");
      } else {
        boolean cadastrou = cadastrarAluno(nome, ra);
        if (cadastrou) {
          exibirAlerta(
            Alert.AlertType.INFORMATION,
            "Sucesso",
            "Aluno cadastrado com sucesso!"
          );
        } else {
          exibirAlerta(
            Alert.AlertType.ERROR,
            "Erro",
            "Ocorreu um erro ao cadastrar o aluno."
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

    Scene scene = new Scene(vbox, 300, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }

  private static boolean cadastrarAluno(String nome, String ra) {
    if (DAOaluno.inserirAluno(ra, nome)) {
      return true;
    } else {
      return false;
    }
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
