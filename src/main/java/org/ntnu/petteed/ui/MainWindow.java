package org.ntnu.petteed.ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.ntnu.petteed.Model.BattleSimulator;
import org.ntnu.petteed.Model.Simulator;

/**
 * The main window which assigns necessary information to controllers and starts the application
 *
 */
public class MainWindow extends Application {

  private static final int DEFUALT_WIDTH = 1500;
  private static final int DEFUALT_HEIGHT = 900;

  private BattleSimulator simulator;

  private Stage primaryStage;

  private Scene homePageScene;
  private Scene createArmyScene;
  private Scene simulaitonScene;

  @Override
  public void start(Stage primaryStage) throws Exception {

    this.simulator = new Simulator();
    this.primaryStage = primaryStage;

    // Homepage
    FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/Homepage.fxml"));
    Parent homePagePane = homePageLoader.load();
    this.homePageScene = new Scene(homePagePane,DEFUALT_WIDTH,DEFUALT_HEIGHT);

    //Simulation page
    FXMLLoader simulationPageLoader = new FXMLLoader(getClass().getResource("/Simulation.fxml"));
    Parent simulationPagePane = simulationPageLoader.load();
    this.simulaitonScene = new Scene(simulationPagePane,DEFUALT_HEIGHT,DEFUALT_WIDTH);

    // Simulation Controller
    SimulationController simulationController = simulationPageLoader.getController();
    simulationPageLoader.setController(simulationController);
    simulationController.setSimulator(this.simulator);

    // Army creation page
    FXMLLoader createArmyPageLoader = new FXMLLoader(getClass().getResource("/CreateArmy.fxml"));
    Parent armyCreationPane = createArmyPageLoader.load();
    this.createArmyScene = new Scene(armyCreationPane, DEFUALT_WIDTH, DEFUALT_HEIGHT);

    // CreateArmy Controller
    CreateArmyController createArmyController = createArmyPageLoader.getController();
    createArmyPageLoader.setController(createArmyController);
    createArmyController.setSimulator(this.simulator);
    createArmyController.setReturnScene(this.homePageScene);
    createArmyController.setSimulationScene(simulaitonScene);

    // Homepage Controller
    HomePageController homePageController = homePageLoader.getController();
    homePageLoader.setController(homePageController);
    homePageController.setNextScene(createArmyScene);

    // Sets warning upon exit
    this.primaryStage.setOnCloseRequest(event -> {
      event.consume();
      exitApplication(this.primaryStage);
    });

    this.primaryStage.setTitle("Battle simulator");
    this.primaryStage.setMinHeight(DEFUALT_HEIGHT);
    this.primaryStage.setMinWidth(DEFUALT_WIDTH);
    this.primaryStage.setMaximized(false);
    this.primaryStage.setScene(homePageScene);
    this.primaryStage.show();
  }

  /**
   * Closes the application when the exit button is pressed.
   * Creates a dialog to choose whether to exit or not.
   *
   * @param stage The stage to close.
   */
  protected void exitApplication(Stage stage) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Exit");
    alert.setHeaderText("You are about to exit Battle Simulator!");
    alert.setContentText("Are you sure you want to exit?");

    if(alert.showAndWait().get() == ButtonType.OK) {
      stage.close();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
