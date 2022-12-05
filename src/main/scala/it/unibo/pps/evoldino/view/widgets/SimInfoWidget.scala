package it.unibo.pps.evoldino.view.widgets

import com.sun.javafx.scene.traversal.ContainerTabOrder
import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.model.world.{ WorldConstants, WorldSnapshot }
import it.unibo.pps.evoldino.view.components.GenericButton
import scalafx.geometry.{ Insets, Pos }
import scalafx.scene.control.Label
import scalafx.scene.control.Tooltip
import scalafx.scene.layout.{ Background, BackgroundFill, TilePane }
import scalafx.scene.paint.Color
import scalafx.scene.text.TextAlignment

object SimInfoWidget:

  def updateRender(snapshot: WorldSnapshot): Unit =
    dinosaurNumber.text = "Numero Dinosauri: " + snapshot.livingPopulation().size
    dinosaurAge.text = "Età media: " +
      (snapshot.livingPopulation().size > 0 match
        case true =>
          snapshot.livingPopulation().map(_.age).sum / snapshot
            .livingPopulation()
            .size
        case false => ""
      )
    temperature.text = "Temperatura: " + snapshot.environment.temperature + "°"
    humidity.text = "Umidità: " + snapshot.environment.humidity + "g.m^3"
    vegetation.text = "Vegetazione: " +
      snapshot.environment.vegetationAvailable * 100 / WorldConstants.max_vegetation_value
      + "%"
    if (snapshot.disasters.isEmpty)
      disasters.text = ""
    else
      for dis <- snapshot.disasters
      yield disasters.text = ("/" + disasters.getText + dis.name + "/ ")

  val dinosaurNumber = new Label:
    alignmentInParent = Pos.Center
    alignment = Pos.Center
    text = "Numero Dinosauri: /"
    textAlignment = TextAlignment.Center
    margin = Insets(0, 1, 15, 1)

  val dinosaurAge = new Label:
    alignmentInParent = Pos.Center
    alignment = Pos.Center
    text = "Età media: /"
    textAlignment = TextAlignment.Center
    margin = Insets(0, 1, 15, 1)

  val temperature = new Label:
    alignmentInParent = Pos.Center
    alignment = Pos.Center
    text = "Temperatura: /"
    textAlignment = TextAlignment.Center
    margin = Insets(0, 1, 15, 1)

  val humidity = new Label:
    alignmentInParent = Pos.Center
    alignment = Pos.Center
    text = "Umidità: /"
    textAlignment = TextAlignment.Center
    margin = Insets(0, 1, 15, 1)

  val vegetation = new Label:
    alignmentInParent = Pos.Center
    alignment = Pos.Center
    text = "Vegetazione: /"
    textAlignment = TextAlignment.Center
    margin = Insets(0, 1, 15, 1)

  val disasters = new Label:
    alignmentInParent = Pos.Center
    alignment = Pos.Center
    text = ""
    textAlignment = TextAlignment.Center
    margin = Insets(0, 1, 15, 1)

  val simInfoWidget: TilePane =
    new TilePane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))

      children ++= Seq(dinosaurNumber, dinosaurAge, temperature, humidity, vegetation, disasters)

  simInfoWidget.alignment = Pos.BaselineLeft
