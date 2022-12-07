package it.unibo.pps.evoldino.view.components

import scalafx.scene.control.{ Button, Tooltip }

class GenericButton(val label: String, val tooltipString: String) extends Button(label) {
  style = "-fx-background-radius: 30; -fx-background-color: green"
  tooltip = Tooltip(tooltipString)
}
