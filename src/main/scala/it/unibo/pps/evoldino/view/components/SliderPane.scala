package it.unibo.pps.evoldino.view.components

import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.model.world.WorldConstants
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
      updateMethod: (Float) => Unit): SliderPane =
    SliderPaneImpl(name, iconPath, measureUnit, updateMethod)

  private class SliderPaneImpl(
      name: String,
      iconPath: String,
      measureUnit: String,
      updateMethod: (Float) => Unit)
      extends SliderPane:
    margin = Insets(15)

    protected val slider: Slider = new Slider:
      min = WorldConstants.min_temperature
      max = WorldConstants.max_temperature
      value = WorldConstants.min_temperature
      tooltip = Tooltip(this.getValue() + " " + measureUnit)

    slider.valueProperty.addListener((_, _, newVal: Number) =>
      updateMethod(newVal.floatValue())
      updateSlider(newVal.floatValue() + " " + measureUnit)
    )

    left = new Label:

      graphic = new ImageView:
        fitHeight = 30
        fitWidth = 30
        // image = new Image(iconPath)
      tooltip = Tooltip(name)
    right = slider

    def update(newValue: Number): Unit =
      if !slider.isHover then
        slider.value = newValue.doubleValue()
        slider.tooltip = Tooltip(slider.getValue() + " " + measureUnit)

    def updateSlider(message: String) =
      slider.tooltip = Tooltip(message)
}
