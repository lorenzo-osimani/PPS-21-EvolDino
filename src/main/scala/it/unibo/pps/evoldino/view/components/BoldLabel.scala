package it.unibo.pps.evoldino.view.components

import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.text.{Font, FontWeight}

object BoldLabel:
  def apply(title: String): Label = new Label:
    text = title
    padding = Insets(5, 0, 5, 10)
    font = Font.font("Arial", FontWeight.Bold, 12)