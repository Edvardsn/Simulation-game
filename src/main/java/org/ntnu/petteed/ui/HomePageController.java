package org.ntnu.petteed.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * This class represents the controller for the HomePage of the application.
 *
 */
public class HomePageController {

  private Scene nextScene;

  @FXML
  private Button playButton;

  /**
   * Opens the next scene from the homepage
   *
   * @param actionEvent The event which triggers the transition
   */
  public void openNextScene(ActionEvent actionEvent){
    Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
    primaryStage.hide();
    primaryStage.setScene(nextScene);
    primaryStage.show();
  }

  /**
   * Sets the scene which is next
   *
   * @param newScene The next scene
   */
  public void setNextScene(Scene newScene){
    this.nextScene = newScene;
  }

}
