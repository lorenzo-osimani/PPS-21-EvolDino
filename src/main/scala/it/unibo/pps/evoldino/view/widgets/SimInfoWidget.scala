package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.model.world.{WorldConstants, WorldSnapshot}
import it.unibo.pps.evoldino.view.components.{ValueLabel, BoldLabel}
import scalafx.geometry.{Insets, Orientation, Pos}
import scalafx.scene.layout.{Background, BackgroundFill, FlowPane, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight, TextAlignment}

import scala.collection.mutable.ListBuffer

object SimInfoWidget:

  def updateRender(snapshot: WorldSnapshot): Unit =
    dinosaurNumber.modifyValue(snapshot.livingPopulation().size.toString)
    dinosaurAge.modifyValue(
      snapshot.livingPopulation().size > 0 match
        case true =>
          (snapshot.livingPopulation().map(_.age).sum / snapshot
            .livingPopulation()
            .size).toString
        case false => "")
    temperature.modifyValue(snapshot.environment.temperature.toString + "°")
    humidity.modifyValue(snapshot.environment.humidity.toString + "g.m^3")
    vegetation.modifyValue((snapshot.environment.vegetationAvailable * 100 / WorldConstants.max_vegetation_value).toString
      + "%")
    iteration.modifyValue(snapshot.number_iteration.toString)

  private class InfoLog(title: String, value: String ="/") extends FlowPane:
    val valueLabel = ValueLabel(value)
    children = Seq(BoldLabel(title),  valueLabel)
    maxWidth = 190
    def modifyValue(newValue: String) = valueLabel.text = newValue

  private val dinosaurNumber = new InfoLog("Numero Dinosauri: ")

  private val dinosaurAge = new InfoLog("Età media: ")

  private val temperature = new InfoLog("Numero Iterazioni: ")

  private val humidity = new InfoLog("Temperatura: ")

  private val vegetation = new InfoLog("Umidità: ")

  private val iteration = new InfoLog("Vegetazione: ")

  val simInfoWidget: GridPane =
    new GridPane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      padding = Insets(5, 0, 5, 0)
      add(new GridPane:
        add(dinosaurNumber , 0, 0)
        add(dinosaurAge , 1, 0)
        add(iteration , 2, 0)
        add(temperature , 0, 1)
        add(humidity , 1, 1)
        add(vegetation , 2, 1)
      , 0, 0)
      add(DisastersLogWidget.disasterLogWidget, 0, 1)
