package org.ntnu.petteed.ui;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.BattleSimulator;

/**
 * This class represents the controller for the Simulation page. The controller controls the
 * simulation of battles and how the user navigates the application to and from the page.
 *
 */
public class SimulationController {

  private Scene returnScene;

  private BattleSimulator simulator;

  // Labels
  @FXML
  private BorderPane borderPane;
  @FXML
  private Text armyOneName;
  @FXML
  private Text armyTwoName;

  // Battle indicators
  @FXML
  private MFXProgressBar armyOneAliveIndicator;
  @FXML
  private MFXProgressBar armyTwoAliveIndicator;
  @FXML
  private MFXProgressSpinner battleProgressIndicator;

  // Controls
  @FXML
  private MFXComboBox terrainComboBox;
  private ObservableList<String> terrainChoices = FXCollections.observableArrayList();

  @FXML
  private MFXButton resetCombatButton;
  @FXML
  private MFXButton simulateBattleButton;

  // Table army two
  @FXML
  private TableView<Actor> armyOneTable = new TableView<>();
  @FXML
  private TableColumn<Actor,String> armyOneNameColumn;
  @FXML
  private TableColumn<Actor,Integer> armyOneHealthColumn;

  private final ObservableList<Actor> armyOneObservableList = FXCollections.observableArrayList();

  // Table army one
  @FXML
  private TableView<Actor> armyTwoTable = new TableView<>();
  @FXML
  private TableColumn<Actor,String> armyTwoNameColumn;
  @FXML
  private TableColumn<Actor,Integer> armyTwoHealthColumn;

  private final ObservableList<Actor> armyTwoObservableList= FXCollections.observableArrayList();

  @FXML
  private Text armyWinnerText;


  /**
   * Initializes the tableview and terrain graphical elements of the page
   *
   */
  public void initialize(){
    // Terrain checkboxes
    terrainChoices.add("Forest");
    terrainChoices.add("Hills");
    terrainChoices.add("Plains");
    terrainComboBox.setItems(terrainChoices);

    // Table first army
    armyOneTable.setItems(armyOneObservableList);
    armyOneNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    armyOneHealthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));

    // Table second Army
    armyTwoTable.setItems(armyTwoObservableList);
    armyTwoNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    armyTwoHealthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));

  }

  /**
   * Sets the simulator for the scene
   *
   * @param simulator The simulator for the scene
   */
  public void setSimulator(BattleSimulator simulator){
    this.simulator = simulator;
  }

  /**
   *  Resets combat to the state in which simulation was initially
   *
   */
  public void resetCombat(){
    try{
      this.simulator.resetCombat();

      // Create the new battle
      this.simulator.createBattle((String)this.terrainComboBox.getSelectedItem());

      updateAllGraphicalElements();

      armyWinnerText.setText("Winner: ");
    }
     catch (Exception e){
      showUserInputAlert("Unable to reset current combat");
    }
  }

  /**
   * Simulates a battle between the armies in the simulator
   *
   */
  public void simulateBattle() {
    try{
      this.simulator.createBattle((String) terrainComboBox.getSelectedItem());

      Army winningArmy = this.simulator.battle();

      updateAllGraphicalElements();

      armyWinnerText.setText("The Winner is " + winningArmy.getName() + "!");

    }
    catch (IllegalArgumentException e){
      showUserInputAlert(e.getMessage());
    }
    catch(Exception e){
      showUnexpectedErrorAlert("Unable to simulate combat");
    }
  }


  /**
   * Updates the graphical elements of the page
   *
   */
  public void updateAllGraphicalElements() {

    // Updates labels
    armyOneName.setText(this.simulator.getFirstArmy().getName());
    armyTwoName.setText(this.simulator.getSecondArmy().getName());

    // Updates battle progress
    armyOneAliveIndicator.setProgress(this.simulator.getPercentageOfActorsAliveArmyOne());
    armyTwoAliveIndicator.setProgress(this.simulator.getPercentageOfActorsAliveArmyTwo());
    battleProgressIndicator.setProgress(this.simulator.getTotalPercentageOfActorsAlive());

    armyOneObservableList.clear();
    armyTwoObservableList.clear();

    // Updates tableview
    armyOneObservableList.addAll(this.simulator.getFirstArmy().getActors());
    armyTwoObservableList.addAll(this.simulator.getSecondArmy().getActors());
  }

  /**
   * Alerts the user when invalid input has been input
   *
   * @param message A message of what triggered the input alert
   */
  private void showUserInputAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Input Error");
    alert.setHeaderText("Input Error");
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Alerts the user of an unexcpected error
   *
   */
  private void showUnexpectedErrorAlert(String message) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Unexpected Error");
    alert.setHeaderText("Unexcpected error Error");
    alert.setContentText(message);
    alert.showAndWait();
  }


}
