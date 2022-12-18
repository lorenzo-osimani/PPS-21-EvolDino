package it.unibo.pps.evoldino.view.components

import scalafx.geometry.{ Insets, Pos }
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.BorderPane
import scalafx.scene.text.TextAlignment
import scalafx.stage.Stage

object EndDialog:
  def apply(dialogTitle: String, message: String): Stage = new Stage:
    title = dialogTitle
    scene = new Scene:
      root = new BorderPane:
        padding = Insets(10)

        top = new Label:
          alignmentInParent = Pos.Center
          alignment = Pos.Center
          text = message
          textAlignment = TextAlignment.Center
          margin = Insets(0, 5, 15, 5)
    resizable = false
