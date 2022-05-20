package org.ntnu.petteed.ui;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXComboBox;
import io.github.palexdev.materialfx.controls.MFXProgressBar;
import io.github.palexdev.materialfx.controls.MFXProgressSpinner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.BattleSimulator;

public class SimulationController {

  private Scene returnScene;

  private BattleSimulator simulator;


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

  private MFXButton resetCombatButton;

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
    terrainChoices.add("Forest");
    terrainChoices.add("Hills");
    terrainChoices.add("Plains");
    terrainComboBox.setItems(terrainChoices);

    armyOneTable.setItems(armyOneObservableList);
    armyOneNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    armyOneHealthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));

    armyTwoTable.setItems(armyTwoObservableList);
    armyTwoNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    armyTwoHealthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));

  }

  /**
   *  Resets combat to the state in which simulation was initially
   *
   */
  public void resetCombat(){
    this.simulator.getFirstArmy().getActors().clear();
    this.simulator.getFirstArmy().getActors().addAll(this.simulator.getArmiesRegister().get(0).getActors());

    this.simulator.getSecondArmy().getActors().clear();
    this.simulator.getSecondArmy().getActors().addAll(this.simulator.getArmiesRegister().get(1).getActors());

    this.simulator.createBattle((String) terrainComboBox.getSelectedItem());
    updateAllGraphicalElements();
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
   * Simulates a battle between the armies in the simulator
   *
   */
  public void simulateBattle(){
    this.simulator.createBattle((String) terrainComboBox.getSelectedItem());

    Army winningArmy = this.simulator.battle();
    updateAllGraphicalElements();

    armyWinnerText.setText("The Winner is " + winningArmy.getName() + "!");
  }

  /**
   * Updates the graphical elements of the page
   *
   */
  public void updateAllGraphicalElements() {

    armyOneName.setText(this.simulator.getFirstArmy().getName());
    armyTwoName.setText(this.simulator.getSecondArmy().getName());

    armyOneAliveIndicator.setProgress(this.simulator.getPercentageOfActorsAliveArmyOne());
    armyTwoAliveIndicator.setProgress(this.simulator.getPercentageOfActorsAliveArmyTwo());
    battleProgressIndicator.setProgress(this.simulator.getTotalPercentageOfActorsAlive());

    if (this.simulator.getArmiesRegister() != null) {
      armyOneObservableList.clear();
      armyTwoObservableList.clear();
      armyOneObservableList.addAll(simulator.getFirstArmy().getActors());
      armyTwoObservableList.addAll(simulator.getSecondArmy().getActors());
    }
  }
}
