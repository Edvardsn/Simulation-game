module Wargames {

  requires javafx.controls;
  requires javafx.graphics;
  requires javafx.fxml;
  requires MaterialFX;
  requires org.kordamp.ikonli.elusive;
  requires org.kordamp.ikonli.javafx;

  opens org.ntnu.petteed.ui to javafx.fxml,javafx.graphics;
  exports org.ntnu.petteed.ui to javafx.graphics,javafx.fxml;

  exports org.ntnu.petteed.Model;
  exports org.ntnu.petteed.Model.EventMechanics;
  exports org.ntnu.petteed.Model.Effects;
}