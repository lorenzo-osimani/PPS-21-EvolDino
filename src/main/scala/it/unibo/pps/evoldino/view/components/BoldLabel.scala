package it.unibo.pps.evoldino.view.components

import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.text.{Font, FontWeight}

class BoldLabel(title: String) extends Label:
  text = title
  padding = Insets(5, 0, 5, 10)
  font = Font.font("Arial", FontWeight.Bold, 12)