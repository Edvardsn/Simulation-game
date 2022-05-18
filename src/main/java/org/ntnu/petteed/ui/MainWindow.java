package org.ntnu.petteed.ui;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import org.ntnu.petteed.Model.BattleSimulator;
import org.ntnu.petteed.Model.Simulator;

public class MainWindow extends Application {

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
    this.homePageScene = new Scene(homePagePane,1450,800);

    HomePageController homePageController = homePageLoader.getController();
    homePageLoader.setController(homePageController);

    // Army creation page
    FXMLLoader createArmyPageLoader = new FXMLLoader(getClass().getResource("/CreateArmy.fxml"));
    Parent armyCreationPane = createArmyPageLoader.load();
    this.createArmyScene = new Scene(armyCreationPane, 1450, 800);


    CreateArmyController createArmyController = createArmyPageLoader.getController();
    createArmyPageLoader.setController(createArmyController);
    createArmyController.setSimulator(this.simulator);
    createArmyController.setReturnScene(this.homePageScene);



    this.primaryStage.setTitle("Battle simulator");
    this.primaryStage.setMinHeight(900);
    this.primaryStage.setMinWidth(1500);
    this.primaryStage.setMaximized(false);
    this.primaryStage.setScene(createArmyScene);
    this.primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}
