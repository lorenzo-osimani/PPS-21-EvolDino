package it.unibo.pps.evoldino.view.components

import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.text.{Font, FontWeight}

object ValueLabel:
  def apply(value: String, min_width: Double = 30): Label = new Label:
    text = value
    padding = Insets(5, 10, 5, 0)
    minWidth = min_width
    font = Font.font("Arial", FontWeight.Light, 12)
