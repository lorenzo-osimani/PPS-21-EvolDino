package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.model.world.{WorldConstants, WorldSnapshot}
import it.unibo.pps.evoldino.view.components.{ValueLabel, BoldLabel}
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.layout.{Background, BackgroundFill, FlowPane}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight, TextAlignment}

import scala.collection.mutable.ListBuffer

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
    temperature.text = snapshot.environment.temperature.toString + "°"
    humidity.text = snapshot.environment.humidity.toString + "g.m^3"
    vegetation.text = (snapshot.environment.vegetationAvailable * 100 / WorldConstants.max_vegetation_value).toString
      + "%"
    iteration.text = snapshot.number_iteration.toString

  private val dinosaurNumber = new ValueLabel("/", 40)

  private val dinosaurAge = new ValueLabel("/", 40)

  private val temperature = new ValueLabel("/", 60)

  private val humidity = new ValueLabel("/", 80)

  private val vegetation = new ValueLabel("/", 80)

  private val iteration = new ValueLabel("/", 80)

  val simInfoWidget: FlowPane =
    new FlowPane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      padding = Insets(10, 0, 0, 0)
      alignment = Pos.TopLeft
      children ++= Seq(new BoldLabel("Numero Dinosauri:"), dinosaurNumber,
        new BoldLabel("Età media:"), dinosaurAge,
        new BoldLabel("Temperatura:"), temperature,
        new BoldLabel("Umidità:"), humidity,
        new BoldLabel("Vegetazione:"), vegetation, DisastersLogWidget.disasterLogWidget,
        new BoldLabel("Numero Iterazione:"), iteration)