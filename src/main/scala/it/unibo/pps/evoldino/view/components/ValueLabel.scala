package it.unibo.pps.evoldino.view.components

import scalafx.geometry.Insets
import scalafx.scene.control.Label
import scalafx.scene.text.{Font, FontWeight}

class ValueLabel(value: String, minWidth: Double = 30) extends Label:
  text = value
  padding = Insets(5, 10, 5, 0)
  minWidth = minWidth
  font = Font.font("Arial", FontWeight.Light, 12)
