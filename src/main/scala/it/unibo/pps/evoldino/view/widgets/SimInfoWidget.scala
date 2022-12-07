package it.unibo.pps.evoldino.view.widgets

import com.sun.javafx.scene.traversal.ContainerTabOrder
import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.model.world.{WorldConstants, WorldSnapshot}
import it.unibo.pps.evoldino.view.components.GenericButton
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.control.Label
import scalafx.scene.control.Tooltip
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.{Background, BackgroundFill, FlowPane}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight, TextAlignment}

object SimInfoWidget:

  def updateRender(snapshot: WorldSnapshot): Unit =
    dinosaurNumber.text = snapshot.livingPopulation().size.toString
    dinosaurAge.text =
      snapshot.livingPopulation().size > 0 match
        case true =>
          (snapshot.livingPopulation().map(_.age).sum / snapshot
            .livingPopulation()
            .size).toString
        case false => ""
    temperature.text = snapshot.environment.temperature + "°"
    humidity.text = snapshot.environment.humidity + "g.m^3"
    vegetation.text = snapshot.environment.vegetationAvailable * 100 / WorldConstants.max_vegetation_value
      + "%"
    if (snapshot.disasters.isEmpty)
      disasters.text = ""
    else
      for dis <- snapshot.disasters
      yield
        disasters.text = ("/" + disasters.getText + dis.name + "/ ")

  private class BoldLabel(title: String) extends Label:
    text = title
    padding = Insets(0, 0, 5, 10)
    font = Font.font("Arial", FontWeight.Bold, 12)

  private class ValueLabel(value: String) extends Label:
    text = value
    padding = Insets(0, 10, 5, 0)
    font = Font.font("Arial", FontWeight.Light, 12)

  private val dinosaurNumber = new ValueLabel("/")

  private val dinosaurAge = new ValueLabel("/")

  private val temperature = new ValueLabel("/")

  private val humidity = new ValueLabel("/")

  private val vegetation = new ValueLabel("/")

  private val disasters = new ValueLabel("")

  val simInfoWidget: FlowPane =
    new FlowPane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      padding = Insets(10, 0, 0, 0)
      alignment = Pos.TopLeft
      children ++= Seq(new BoldLabel("Numero Dinosauri:"), dinosaurNumber,
        new BoldLabel("Età media:"), dinosaurAge,
        new BoldLabel("Temperatura:"), temperature,
        new BoldLabel("Umidità:"), humidity,
        new BoldLabel("Vegetazione:"), vegetation,
        disasters)