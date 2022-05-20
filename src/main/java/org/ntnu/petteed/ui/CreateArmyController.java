package org.ntnu.petteed.ui;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.Army;
import org.ntnu.petteed.Model.BattleSimulator;
import org.ntnu.petteed.Model.FileHandler;
import org.ntnu.petteed.Model.ActorType;

/**
 * This class represents the controller for the army creation page. The controller controls the
 * creation of all actors and how the user navigates the application to and from the page.
 *
 */
public class CreateArmyController {

  private Scene returnScene;
  private Scene simulationScene;

  private BattleSimulator simulator;

  // Buttons
  @FXML
  private MFXButton templateArmyButton;
  @FXML
  private MFXButton returnButton;
  @FXML
  private Button loadArmyButton;

  // Textfields
  @FXML
  private MFXTextField armyNameField;
  @FXML
  private Text armyNameLabel;
  @FXML
  private MFXTextField unitNameField;
  @FXML
  private MFXTextField unitHealthField;
  @FXML
  private MFXTextField unitAmountField;

  // Checkboxes
  @FXML
  private MFXCheckbox infantryUnitCheckBox;
  @FXML
  private MFXCheckbox rangedUnitCheckBox;
  @FXML
  private MFXCheckbox cavalryUnitCheckBox;
  @FXML
  private MFXCheckbox commanderUnitCheckBox;
  @FXML
  private MFXCheckbox mageUnitCheckBox;
  @FXML
  private MFXCheckbox supportUnitCheckBox;

  // Tableview
  @FXML
  public TableView<Actor> armyUnitTable = new TableView<>();
  @FXML
  private TableColumn<Actor,String> actorNameColumn = new TableColumn<>();
  @FXML
  private TableColumn<Actor,Integer> actorHealthColumn = new TableColumn<>();
  @FXML
  private TableColumn<Actor,Integer> actorAttackColumn = new TableColumn<>();
  @FXML
  private TableColumn<Actor,Integer> actorArmourColumn = new TableColumn<>();

  private ObservableList<Actor> actorObservableList;

  /**
   * Initializes the tableview of the page
   *
   */
  public void initialize(){
    actorObservableList = FXCollections.observableArrayList();
    armyUnitTable.setItems(actorObservableList);

    actorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    actorHealthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));
    actorAttackColumn.setCellValueFactory(new PropertyValueFactory<>("attackValue"));
    actorArmourColumn.setCellValueFactory(new PropertyValueFactory<>("armour"));

  }

  /**
   * Opens the simulation scene
   *
   * @param actionEvent
   */
  public void openSimulationScene(ActionEvent actionEvent) {
    Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
    primaryStage.hide();
    primaryStage.setMaximized(true);
    primaryStage.setScene(simulationScene);
    primaryStage.show();
  }

  /**
   * Sets the simulator which the controller operates on
   *
   * @param battleSimulator The simulator to operate on
   */
  public void setSimulator(BattleSimulator battleSimulator){
    this.simulator = battleSimulator;
  }

  /**
   * Sets the scene which will be returned to
   *
   * @param scene The scene to return to
   */
  public void setReturnScene(Scene scene){
    this.returnScene = scene;
  }

  /**
   * Sets the scene which will be the simulationscene
   *
   * @param simulationScene The simulation scene
   */
  public void setSimulationScene(Scene simulationScene) {
    this.simulationScene = simulationScene;
  }

  /**
   * Opens the return scene
   *
   * @param actionEvent The event which triggers the transition
   */
  public void openReturnScene(ActionEvent actionEvent){
    Stage primaryStage = (Stage)((Node)actionEvent.getSource()).getScene().getWindow();
    primaryStage.hide();
    primaryStage.setScene(returnScene);
    primaryStage.show();
  }

  /**
   * Updates the observableList to the current state of the simulator
   *
   */
  private void updateObeservableList() {
    this.actorObservableList.clear();
    this.actorObservableList.addAll(this.simulator.getCurrentArmy().getActors());
  }

  /**
   * Imports a file representing an army
   *
   * @param actionEvent The event occurring when pressing the load button
   */
  public void loadArmyButtonPressed(ActionEvent actionEvent){
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);

    File file = fileChooser.showOpenDialog(null);

    if(file != null){
      try{
        Army loadedArmy = FileHandler.readArmyFromFile(file);

        clearCurrentArmy();

        this.simulator.importArmy(loadedArmy);

        updateObeservableList();

      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  public void exportArmyButtonPressed(ActionEvent actionEvent){
    FileChooser fileChooser = new FileChooser();
    FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
    fileChooser.getExtensionFilters().add(extFilter);

    File file = fileChooser.showSaveDialog(null);
    if(file != null){
      try{
        FileHandler.writeToFile(file,simulator.getCurrentArmy());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  /**
   * Adds a given number of actors based on the data obtained from the user
   *
   */
  public void addActorButtonPressed(){

    if(getSelectedMfxCheckboxes().count() > 1 || getSelectedMfxCheckboxes().findAny().isEmpty()){
      showCheckBoxAlert();
    }
    else{
      String unitType = getSelectedMfxCheckboxes().findFirst().get().getText();
      int unitAmount = Integer.parseInt(unitAmountField.getText());
      String unitName = unitNameField.getText();
      int unitHealth = Integer.parseInt(unitHealthField.getText());

      this.simulator.addActors(unitAmount,unitName,unitHealth,ActorType.valueOfString(unitType));

      updateObeservableList();
    }
  }

  /**
   * Returns a stream of the current selected Checkboxes related to actor creation
   *
   * @return A stream of the current selected Checkboxes related to actor creation
   */
  private Stream<MFXCheckbox> getSelectedMfxCheckboxes() {
    Collection<MFXCheckbox> mfxCheckboxes =  new ArrayList<>();

    mfxCheckboxes.add(infantryUnitCheckBox);
    mfxCheckboxes.add(rangedUnitCheckBox);
    mfxCheckboxes.add(cavalryUnitCheckBox);
    mfxCheckboxes.add(commanderUnitCheckBox);
    mfxCheckboxes.add(mageUnitCheckBox);
    mfxCheckboxes.add(supportUnitCheckBox);

    Stream<MFXCheckbox> selectedMfxCheckboxes = mfxCheckboxes.stream().filter(mfxCheckbox -> mfxCheckbox.isSelected());
    return selectedMfxCheckboxes;
  }

  /**
   * Shows the user an alert related to invalid number of checkboxes marked
   *
   */
  private void showCheckBoxAlert() {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Creation Error");
    alert.setHeaderText("Invalid number of marked Checkboxes");
    alert.setContentText("Please select one checkbox when creating units");
    alert.showAndWait();
  }

  /**
   * Creates the current army being edited
   *
   */
  public void finishArmyButtonPressed(ActionEvent event){
    this.simulator.createArmy(armyNameField.getText(),simulator.getCurrentArmy().getActors());
    clearCurrentArmy();

    if(this.simulator.getNumberOfArmies() == 2){
      openSimulationScene(event);
    }
  }

  /**
   * Removes the current selected actor in the tableview
   *
   */
  public void removeActionButtonPressed(){
    this.simulator.removeActor(this.armyUnitTable.getSelectionModel().getSelectedItem());
    updateObeservableList();
  }

  /**
   * Resets the current army being created
   *
   */
  public void clearCurrentArmy(){
    this.simulator.resetArmy();
    actorObservableList.clear();
  }

  /**
   * Creates a template army
   *
   * @param event The event where the user presses the template button
   */
  public void templateButtonPressed(ActionEvent event) {
    clearCurrentArmy();

    this.simulator.addActors(5,"Infantry",100, ActorType.INFANTRY_UNIT);
    this.simulator.addActors(3,"Ranged",100, ActorType.RANGED_UNIT);
    this.simulator.addActors(3,"Cavalry",100, ActorType.CAVALRY_UNIT);
    this.simulator.addActors(1,"Mage",100, ActorType.MAGE_UNIT);
    this.simulator.addActors(1,"Support",100, ActorType.SUPPORT_UNIT);
    this.simulator.addActors(1,"Commander",150, ActorType.COMMANDER_UNIT);

    updateObeservableList();
  }

  /**
   * Updates the name of the Army being created
   *
   * @param keyEvent The event where the user enters the army's new name
   */
  public void updateArmyName(KeyEvent keyEvent) {
    String armyName = armyNameField.getText();
    armyNameLabel.setText(armyName);
  }

  /**
   * Returns the list of observable actors
   *
   * @return The list of observable actors
   */
  public ObservableList<Actor> getActorObservableList() {
    return actorObservableList;
  }
}
