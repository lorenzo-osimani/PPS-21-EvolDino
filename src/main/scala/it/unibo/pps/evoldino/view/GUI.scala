package it.unibo.pps.evoldino.view

import scalafx.geometry.{ Insets, Pos }
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{ Background, BackgroundFill, BorderPane }
import scalafx.scene.paint.Color
import scalafx.scene.text.TextAlignment
import scalafx.stage.{ Screen, Stage }
import it.unibo.pps.evoldino.view.widgets.{ ControlBar, EnviromentConditionsWidget }

object GUI:

  def start(stage: Stage): Unit =

    println("Screen size: " + Screen.primary.bounds.width + "x" + Screen.primary.bounds.height)

    val preferredHeight: Double = Screen.primary.bounds.height * 3 / 4
    val preferredWidth: Double = Screen.primary.bounds.width * 3 / 4

    stage.setTitle("Evoldino")
    stage.setResizable(true)
    stage.setScene(
      new Scene:
        root = new BorderPane:
          background = new Background(Array(new BackgroundFill(Color.Black, null, null)))
          padding = Insets(10)
          bottom = ControlBar.controlBar
          top = EnviromentConditionsWidget.controlBar
    )

    stage.setFullScreen(false)
