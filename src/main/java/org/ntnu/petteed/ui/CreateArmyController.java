package org.ntnu.petteed.ui;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXCheckbox;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.util.ArrayList;
import java.util.Collection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import org.ntnu.petteed.Model.Actor;
import org.ntnu.petteed.Model.BattleSimulator;
import org.ntnu.petteed.Model.UnitFactory;
import org.ntnu.petteed.Model.UnitType;


public class CreateArmyController {

  private Scene returnScene;

  private BattleSimulator simulator;

  @FXML
  private MFXCheckbox infantryUnitCheckBox;

  @FXML
  private MFXButton templateArmyButton;

  @FXML
  private MFXTextField unitNameField;

  @FXML
  private MFXTextField armyNameField;

  @FXML
  private Text armyNameLabel;

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
// Allerede sammensatt

  private ObservableList<Actor> actorsList;

  public void initialize(){
    actorsList = FXCollections.observableArrayList();
    armyUnitTable.setItems(actorsList);

    actorNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    actorHealthColumn.setCellValueFactory(new PropertyValueFactory<>("health"));
    actorAttackColumn.setCellValueFactory(new PropertyValueFactory<>("attackValue"));
    actorArmourColumn.setCellValueFactory(new PropertyValueFactory<>("armour"));
  }

  public ObservableList<Actor> getActorsList() {
    return actorsList;
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

  public void resetCurrentArmy(){
    actorsList.clear();
  }

  public void updateArmyName(KeyEvent keyEvent) {
    String armyName = armyNameField.getText();
    armyNameLabel.setText(armyName);
  }

  public void templateButtonPressed(ActionEvent event) {
    resetCurrentArmy();

    Collection<Actor> templateArmyOne = new ArrayList<>();
    templateArmyOne.addAll(UnitFactory.createUnits(5,"Infantry",100, UnitType.INFANTRY_UNIT));
    templateArmyOne.addAll(UnitFactory.createUnits(3,"Ranged",100, UnitType.RANGED_UNIT));
    templateArmyOne.addAll(UnitFactory.createUnits(3,"Cavalry",100, UnitType.CAVALRY_UNIT));
    templateArmyOne.addAll(UnitFactory.createUnits(1,"Mage",100, UnitType.MAGE_UNIT));
    templateArmyOne.addAll(UnitFactory.createUnits(1,"Support",100, UnitType.SUPPORT_UNIT));

    simulator.createArmy("template",templateArmyOne);

    simulator.getCurrentArmy().getAll().forEach(actor -> getActorsList().add(actor));
  }
}
