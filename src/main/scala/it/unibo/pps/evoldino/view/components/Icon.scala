package it.unibo.pps.evoldino.view.components

import scalafx.scene.control.{Label, Tooltip}
import scalafx.scene.image.{Image, ImageView}

object Icon {
  def apply(path: String, tooltipMessage: String, dimension: Int): Label = new Label:
    graphic = new ImageView:
      fitHeight = dimension
      fitWidth = dimension
      image = new Image(path)
    tooltip = Tooltip(tooltipMessage)
}
