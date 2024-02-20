import javafx.application.Application;
import javafx.stage.Stage;
import view.*;

public class App extends Application {

  static Menu menu = new Menu();

  public static void main(String[] args) {
    menu.launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    menu.start(primaryStage);
  }
}
