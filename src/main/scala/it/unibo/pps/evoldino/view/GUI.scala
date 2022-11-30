package it.unibo.pps.evoldino.view

import it.unibo.pps.evoldino.model.world.WorldSnapshot
import scalafx.geometry.{ Insets, Pos }
import scalafx.scene.Scene
import scalafx.scene.control.Label
import scalafx.scene.layout.{ Background, BackgroundFill, BorderPane }
import scalafx.scene.paint.Color
import scalafx.scene.text.TextAlignment
import scalafx.stage.{ Screen, Stage }
import it.unibo.pps.evoldino.view.widgets.{
  ClimateWidget,
  ControlBar,
  DisastersWidget,
  SimInfoWidget
}

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
          bottom = new BorderPane:
            top = SimInfoWidget.simInfoWidget
            bottom = ControlBar.controlBar
          top = DisastersWidget.disastersWidget
          right = ClimateWidget.climateWidget
    )

    stage.setFullScreen(false)

  def updateRender(snapshot: WorldSnapshot): Unit =
    SimInfoWidget.updateRender(snapshot)
