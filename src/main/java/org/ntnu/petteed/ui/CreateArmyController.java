package org.ntnu.petteed.ui;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Optional;
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
  private void updateObeservableList()  {
    this.actorObservableList.clear();
    this.actorObservableList.addAll(getCurrentHighlightedActors());
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
        this.armyNameField.setText(loadedArmy.getName());
        this.armyNameLabel.setText(loadedArmy.getName());

        updateObeservableList();

      } catch (IOException e) {
        showUserInputAlert("Unable to load army: " + e.getMessage());
      }
    }
  }

  /**
   * Exports the current army to a CSV file
   *
   * @param actionEvent The event triggering the export
   */
  public void exportArmyButtonPressed(ActionEvent actionEvent) {
    if(this.simulator.validHighlightedArmy()) {

      FileChooser fileChooser = new FileChooser();
      FileChooser.ExtensionFilter extFilter =
          new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
      fileChooser.getExtensionFilters().add(extFilter);


      File file = fileChooser.showSaveDialog(null);
      if (file != null) {
        try {

          FileHandler.writeToFile(file,
              new Army(getArmyNameFieldText(),getCurrentHighlightedActors()));
          this.simulator.resetArmy();
          updateObeservableList();


        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    }
    else{
      showUserInputAlert("Cannot export an empty army");
    }
  }


  /**
   * Adds a given number of actors based on the data obtained from the user
   *
   */
  public void addActorButtonPressed(){
    try {
        String unitType = getSelectedCheckbox();
        int unitAmount = Integer.parseInt(getUnitAmountFieldText());
        String unitName = getUnitNameField();
        int unitHealth = Integer.parseInt(getUnitHealthFieldText());

        this.simulator.addActors(unitAmount, unitName, unitHealth,
            ActorType.valueOfString(unitType));

        updateObeservableList();

    } catch (IllegalArgumentException exception){
      showUserInputAlert(exception.getMessage());
    }
  }

  /**
   * Creates the current army being edited
   *
   */
  public void finishArmyButtonPressed(ActionEvent event)  {
    try{
      this.simulator.createArmy(getArmyNameFieldText());
      clearCurrentArmy();
      this.armyNameField.clear();
      this.armyNameLabel.setText("Second Army");

      if(this.simulator.getNumberOfArmies() == 2){
        openSimulationScene(event);
      }
    }
   catch (IllegalArgumentException e){
      showUserInputAlert(e.getMessage());
   }
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
   * @throws IllegalArgumentException if the army field has an invalid value
   */
  public void updateArmyName(KeyEvent keyEvent) throws IllegalArgumentException{
    String armyName = getArmyNameFieldText();
    armyNameLabel.setText(armyName);
  }

  /**
   * Gets the current highlighted actors from the simulator
   *
   * @return The current highlighted actors from the simulator
   */
  public Collection<Actor> getCurrentHighlightedActors(){

    Collection<Actor> highlightedActors = new ArrayList<>();

    Iterator<Actor> currentActorsIterator = this.simulator.getCurrentlyHighlightedActorsIterator();

    while(currentActorsIterator.hasNext()){
      Actor actor = currentActorsIterator.next();

      highlightedActors.add(actor);
    }

    return highlightedActors;
  }

  /**
   * Returns the string value of the army name field in the GUI.
   *
   * @return The name in the army name field
   * @throws IllegalArgumentException if the army field has an invalid value
   */
  private String getArmyNameFieldText() throws IllegalArgumentException {
    if(this.armyNameField.getText().isBlank() || this.armyNameField.getText() == null ){
      throw new IllegalArgumentException("An army needs a name to be created");
    }
    return armyNameField.getText();
  }

  /**
   * Returns the text of the unit name field
   *
   * @return The text of the unit name field
   * @throws IllegalArgumentException if the name field has an invalid value
   */
  private String getUnitNameField() {
    if(unitNameField.getText().isBlank()){
      throw new IllegalArgumentException("Cannot create a unit without a name, please enter a name");
    }
    return this.unitNameField.getText();
  }

  /**
   * Returns the value of the unit health field
   *
   * @return The value of the unit health field
   * @throws IllegalArgumentException if the amount field has an invalid value
   */
  private String getUnitHealthFieldText() {
    if(unitHealthField.getText().isBlank()){
      throw new IllegalArgumentException("Cannot create a unit without any health, please enter a valid value");
    }

    try{
      Integer.parseInt(unitHealthField.getText());
    } catch(NumberFormatException exception){
      throw new IllegalArgumentException("Please enter a numeric value for the unit's health");
    }

    return this.unitHealthField.getText();
  }

  /**
   * Returns the value of the unit amount field
   *
   * @return The value of the unit amount field
   * @throws IllegalArgumentException if the amount field has an invalid value
   */
  private String getUnitAmountFieldText() {
    if(unitAmountField.getText().isBlank()){
      throw new IllegalArgumentException("Invalid amount of units to be created entered, please enter a valid amount");
    }

    try{
      Integer.parseInt(unitAmountField.getText());
    } catch(NumberFormatException exception){
      throw new IllegalArgumentException("Please enter a numeric value for the amount of units to be created");
    }
    return this.unitAmountField.getText();
  }

  /**
   * Returns the name of the checkbox selected
   *
   * @return The name of the selected checkbox
   * @throws IllegalArgumentException if no checkbox or more than one checkbox is selected
   */
  public String getSelectedCheckbox(){

    Collection<MFXCheckbox> mfxCheckboxes = getMfxCheckboxes();

    Optional<MFXCheckbox> selectedBox = mfxCheckboxes
        .stream()
        .filter(MFXCheckbox::isSelected)
        .findFirst();

    if(selectedBox.isPresent()){
      return selectedBox.get().getText();
    }
    else{
      throw new IllegalArgumentException("Invalid number of checkboxes marked");
    }
  }

  /**
   * Returns a collection of the Checkboxes related to actor creation
   *
   * @return A collection of the Checkboxes related to actor creation
   */
  private Collection<MFXCheckbox> getMfxCheckboxes() {

    Collection<MFXCheckbox> mfxCheckboxes =  new ArrayList<>();

    mfxCheckboxes.add(infantryUnitCheckBox);
    mfxCheckboxes.add(rangedUnitCheckBox);
    mfxCheckboxes.add(cavalryUnitCheckBox);
    mfxCheckboxes.add(commanderUnitCheckBox);
    mfxCheckboxes.add(mageUnitCheckBox);
    mfxCheckboxes.add(supportUnitCheckBox);

    return mfxCheckboxes;
  }

}
