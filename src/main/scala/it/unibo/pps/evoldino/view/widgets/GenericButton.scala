package it.unibo.pps.evoldino.view.widgets

import scalafx.scene.control.{ Button, Tooltip }

class GenericButton(val label: String, val tooltipString: String) extends Button(label) {
  this.setStyle("-fx-background-radius: 30; -fx-background-color: yellow")
  this.setTooltip(Tooltip(tooltipString))
}
