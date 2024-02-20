package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Menu extends Application {

  @Override
  public void start(Stage primaryStage) throws FileNotFoundException {
  
    
    
    primaryStage.setTitle("Gerenciador de biblioteca");
    primaryStage.setMaximized(true);
   
   
    Button btnCadastrarLivro = new Button("Cadastrar Livro");
    Button btnCadastrarAluno = new Button("Cadastrar Aluno");
    Button btnEmprestimo = new Button("Emprestar Livro");
    Button btnDevolver = new Button("Devolver Livro");
    Button btnBuscar = new Button("Buscar");
    Button btnSair = new Button("Sair");

    btnCadastrarLivro.setOnAction(e -> {
      CadastrarLivro cadastroLivro = new CadastrarLivro();
      cadastroLivro.start(new Stage());
    });

    btnCadastrarAluno.setOnAction(e -> {
      CadastrarAluno cadastroAluno = new CadastrarAluno();
      cadastroAluno.start(new Stage());
    });

    btnEmprestimo.setOnAction(e -> {
      EmprestarLivro EmprestimoLivro = new EmprestarLivro();
      EmprestimoLivro.start(new Stage());
    });

    btnDevolver.setOnAction(e -> {
      DevolucaoLivro DevolverLivro = new DevolucaoLivro();
      DevolverLivro.start(new Stage());
    });

    btnBuscar.setOnAction(e -> {
      Busca Busca = new Busca();
      Busca.start(new Stage());
    });

    btnSair.setOnAction(e -> primaryStage.close());

    VBox vbox = new VBox(30);
    vbox.setAlignment(Pos.BASELINE_LEFT);
    vbox
      .getChildren()
      .addAll(
        new Label("Selecione uma opção:"),
        
        btnCadastrarLivro,
        btnCadastrarAluno,
        btnEmprestimo,
        btnDevolver,
        btnBuscar,
        btnSair
      );



    Scene scene = new Scene(vbox, 300, 300);
    Image icon = new Image("view\\img\\pngtree-vector-book-icon-png-image_995152.jpg");
    primaryStage.getIcons().add(icon);
    
    BackgroundImage myBI= new BackgroundImage(new Image("view\\img\\4823754_d508394098f8f23.jpg",500,500,false,true),
    BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
    BackgroundSize.DEFAULT);
    vbox.setBackground(new Background(myBI));


    
    primaryStage.setScene(scene);
    primaryStage.show();
    
  }

  public static void main(String[] args) {
    launch(args);
  }
}
