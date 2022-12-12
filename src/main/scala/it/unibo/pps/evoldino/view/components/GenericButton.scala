package it.unibo.pps.evoldino.view.components

import scalafx.geometry.Insets
import scalafx.scene.control.{Button, Tooltip}

class GenericButton(val label: String, val tooltipString: String) extends Button(label) {
  style = "-fx-background-radius: 30; -fx-background-color: lightgreen"
  margin = Insets(0,10,0,0)
  tooltip = Tooltip(tooltipString)
}
