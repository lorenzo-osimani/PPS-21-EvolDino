package it.unibo.pps.evoldino.view.components

import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.world.{ Environment, WorldConstants }
import it.unibo.pps.evoldino.view.components.SliderPane
import scalafx.geometry.Insets
import scalafx.scene.control.{ Label, Slider, Tooltip }
import scalafx.scene.image.{ Image, ImageView }
import scalafx.scene.layout.{ Background, BackgroundFill, BorderPane, TilePane }
import scalafx.scene.paint.{ Color, LinearGradient, Stops }

trait SliderPane extends BorderPane:
  def update(newValue: Number): Unit

object SliderPane {

  def apply(
      name: String,
      iconPath: String,
      measureUnit: String,
      min_value: Double,
      max_value: Double,
      starting_value: Double,
      updateMethod: (Float) => Unit): SliderPane =
    SliderPaneImpl(name, iconPath, measureUnit, updateMethod, min_value, max_value, starting_value)

  private class SliderPaneImpl(
      name: String,
      iconPath: String,
      measureUnit: String,
      updateMethod: (Float) => Unit,
      min_value: Double,
      max_value: Double,
      starting_value: Double)
      extends SliderPane:
    margin = Insets(15)

    protected val slider: Slider = new Slider:
      min = min_value
      max = max_value
      value = starting_value
      tooltip = Tooltip(this.getValue() + " " + measureUnit)

    slider.valueProperty.addListener((_, _, newVal: Number) =>
      val value = (newVal.floatValue() * 10).round / 10.toFloat
      updateMethod(value)
      this.slider.value = value
      this.slider.tooltip = Tooltip(value + " " + measureUnit)
    )

    left = Icon(iconPath, name, 20)
    right = slider

    def update(newValue: Number): Unit =
      if !slider.isHover then
        slider.value = newValue.doubleValue()
        slider.tooltip = Tooltip(slider.getValue() + " " + measureUnit)
}
