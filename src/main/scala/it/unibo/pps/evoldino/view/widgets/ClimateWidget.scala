package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.world.{Environment, WorldConstants}
import it.unibo.pps.evoldino.view.components.SliderPane
import scalafx.geometry.*
import scalafx.scene.control.Label
import scalafx.scene.layout.{Background, BackgroundFill, GridPane}
import scalafx.scene.paint.Color
import scalafx.scene.text.{Font, FontWeight, TextAlignment}

object ClimateWidget:

  val climateGridTitle = new Label:
    text = "Gestione Ambiente"
    textAlignment = TextAlignment.Center
    margin = Insets(10, 5, 2, 10)
    font = Font.font("Arial", FontWeight.Bold, 15)

  val tempSlider = SliderPane(
    "Temperature",
    "temperature.png",
    "°",
    WorldConstants.min_temperature,
    WorldConstants.max_temperature,
    Environment.BasicEnvironment.temperature,
    Controller.modifyTemp
  )

  val humSlider = SliderPane(
    "Humidity",
    "humidity.png",
    "g.m^3",
    WorldConstants.min_humidity,
    WorldConstants.max_humidity,
    Environment.BasicEnvironment.humidity,
    Controller.modifyHum
  )

  val vegSlider = SliderPane(
    "Vegetation",
    "vegetation.png",
    "%",
    0,
    100,
    5,
    (veg) => Controller.modifyVeg(veg * WorldConstants.max_vegetation_value / 100)
  )

  def update(temp: Float, hum: Float, veg: Float): Unit =
    tempSlider.update(temp)
    humSlider.update(hum)
    vegSlider.update(veg * 100 / WorldConstants.max_vegetation_value)

  val climateWidget: GridPane =
    new GridPane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      this.add(climateGridTitle, 0, 0)
      this.add(tempSlider, 0, 1)
      this.add(humSlider, 0, 2)
      this.add(vegSlider, 0, 3)
