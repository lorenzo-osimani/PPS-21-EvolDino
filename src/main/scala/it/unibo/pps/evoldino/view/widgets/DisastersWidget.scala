package it.unibo.pps.evoldino.view.widgets

import scalafx.scene.layout.{Background, BackgroundFill, GridPane}
import scalafx.scene.control.{CheckBox, Label}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight, TextAlignment}
import scalafx.geometry.*
import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.disaster.Disaster
import it.unibo.pps.evoldino.view.components.{GenericButton, SliderPane}
import scalafx.application.Platform

object DisastersWidget:

  val title = new Label:
    text = "Controllo Simulazione"
    textAlignment = TextAlignment.Center
    margin = Insets(0, 2, 5, 5)
    font = Font.font("Arial", FontWeight.Bold, 15)

  val disasterGridTitle = new Label:
    text = "Gestione Disastri"
    textAlignment = TextAlignment.Center
    margin = Insets(5, 2, 5, 5)
    font = Font.font("Arial", FontWeight.Bold, 15)

  /* ChangeSpeedButton definition */
  val manualModeButton = new CheckBox("Manual Mode"):
    margin = Insets(5, 2, 5, 10)
    onAction = _ =>
      Controller.setManualMode(this.isSelected)
      disasterGrid.disable = !this.isSelected

  val iceAgeButton = new GenericButton("Ice Age", "Generate Ice Age"):
    onAction = _ => Controller.addDisaster(Disaster.IceAge)

  val droughtButton = new GenericButton("Drought", "Generate Drought"):
    onAction = _ => Controller.addDisaster(Disaster.Drought)

  val earthquakeButton = new GenericButton("Earthquake", "Generate Earthquake"):
    onAction = _ => Controller.addDisaster(Disaster.Earthquake())

  val meteoriteButton = new GenericButton("Meteorite", "Generate Meteorite"):
    onAction = _ => Controller.addDisaster(Disaster.Meteorite())

  def resetButtons() =
    manualModeButton.setSelected(false)
    disasterGrid.disable = true

  val disasterGrid = new GridPane:
    background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
    disable = true
    padding = Insets(5)
    hgap = 5
    vgap = 5
    this.add(iceAgeButton, 0, 0)
    this.add(droughtButton, 1, 0)
    this.add(earthquakeButton, 0, 1)
    this.add(meteoriteButton, 1, 1)

  val disastersWidget: GridPane =
    new GridPane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      padding = Insets(5)
      this.add(title, 0, 0)
      this.add(manualModeButton, 0, 1)
      this.add(disasterGridTitle, 0, 2)
      this.add(disasterGrid, 0, 3)
