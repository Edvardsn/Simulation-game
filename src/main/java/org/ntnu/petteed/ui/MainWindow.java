package org.ntnu.petteed.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.ntnu.petteed.Model.BattleSimulator;
import org.ntnu.petteed.Model.Simulator;

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

    // LOADING REKKEFØLGE

    // Homepage
    FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/Homepage.fxml"));
    Parent homePagePane = homePageLoader.load();
    this.homePageScene = new Scene(homePagePane,DEFUALT_WIDTH,DEFUALT_HEIGHT);

    //Simulation page
    FXMLLoader simulationPageLoader = new FXMLLoader(getClass().getResource("/Simulation.fxml"));
    Parent simulationPagePane = simulationPageLoader.load();
    this.simulaitonScene = new Scene(simulationPagePane,DEFUALT_HEIGHT,DEFUALT_WIDTH);

    SimulationController simulationController = simulationPageLoader.getController();
    simulationPageLoader.setController(simulationController);
    simulationController.setSimulator(this.simulator);

    // Army creation page
    FXMLLoader createArmyPageLoader = new FXMLLoader(getClass().getResource("/CreateArmy.fxml"));
    Parent armyCreationPane = createArmyPageLoader.load();
    this.createArmyScene = new Scene(armyCreationPane, DEFUALT_WIDTH, DEFUALT_HEIGHT);


    CreateArmyController createArmyController = createArmyPageLoader.getController();
    createArmyPageLoader.setController(createArmyController);
    createArmyController.setSimulator(this.simulator);
    createArmyController.setReturnScene(this.homePageScene);
    createArmyController.setSimulationScene(simulaitonScene);

    // Last because have to initialize all other page's first
    HomePageController homePageController = homePageLoader.getController();
    homePageLoader.setController(homePageController);
    homePageController.setNextScene(createArmyScene);

    this.primaryStage.setTitle("Battle simulator");
    this.primaryStage.setMinHeight(DEFUALT_HEIGHT);
    this.primaryStage.setMinWidth(DEFUALT_WIDTH);
    this.primaryStage.setMaximized(false);
    this.primaryStage.setScene(homePageScene);
    this.primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
