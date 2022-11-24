package it.unibo.pps.evoldino.view.widgets

import com.sun.javafx.scene.traversal.ContainerTabOrder
import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.controller.engine.EngineConstants
import scalafx.geometry.Pos
import scalafx.scene.layout.{ Background, BackgroundFill, TilePane }
import scalafx.scene.control.Tooltip
import scalafx.scene.paint.Color
import it.unibo.pps.evoldino.view.widgets.GenericButton
import javafx.event.{ ActionEvent, EventHandler }

object ControlBar:

  /* PlayButton definition */
  val playButton = new GenericButton("Start", "Start the simulation")

  playButton.onAction = startSimEventHandler

  val stopButton = new GenericButton("Stop", "Stop the simulation")

  stopButton.onAction = _ =>
    Controller.endSimulation()
    changeSpeedButton.disable = true
    stopButton.disable = true
    playButton.onAction = startSimEventHandler
    playButton.setText("Start")

  /* ChangeSpeedButton definition */
  val changeSpeedButton = new GenericButton("x1", "Change simulation speed")
  changeSpeedButton.disable = true

  changeSpeedButton.onAction = _ =>
    Controller.changeSpeed() match
      case EngineConstants.ITERATION_MS_1X => changeSpeedButton.setText("x1")
      case EngineConstants.ITERATION_MS_2X => changeSpeedButton.setText("x2")
      case EngineConstants.ITERATION_MS_4X => changeSpeedButton.setText("x4")

  private lazy val startSimEventHandler: EventHandler[ActionEvent] = _ =>
    playButton.text = "Pause"
    playButton.tooltip = Tooltip("Pause the simulation")
    stopButton.setDisable(false)
    playButton.onAction = _ =>
      Controller.isSimulationPlaying() match
        case true =>
          Controller.pauseSimulation()
          playButton.text = "Unpause"
          playButton.tooltip = Tooltip("Unpause the simulation")
        case false =>
          Controller.unpauseSimulation()
          playButton.text = "Pause"
          playButton.tooltip = Tooltip("Pause the simulation")
    Controller.startSimulation()

  val controlBar: TilePane =
    new TilePane:
      background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
      children ++= Seq(playButton, changeSpeedButton, stopButton)

  controlBar.alignment = Pos.BaselineLeft
