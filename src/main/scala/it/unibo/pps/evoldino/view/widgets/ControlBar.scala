package it.unibo.pps.evoldino.view.widgets

import it.unibo.pps.evoldino.controller.Controller
import it.unibo.pps.evoldino.controller.engine.EngineConstants
import it.unibo.pps.evoldino.view.components.GenericButton
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.layout.{Background, BackgroundFill, FlowPane}
import scalafx.scene.control.Tooltip
import scalafx.scene.paint.Color
import javafx.event.{ActionEvent, EventHandler}

object ControlBar:

  /* PlayButton definition */
  val playButton = new GenericButton("Start", "Start the simulation"):
    alignmentInParent = Pos.Center
    onAction = startSimEventHandler

  val stopButton = new GenericButton("Stop", "Stop the simulation"):
    disable = true
    onAction = _ => Controller.endSimulation()

  /* ChangeSpeedButton definition */
  val changeSpeedButton = new GenericButton("x1", "Change simulation speed"):
    disable = true

    onAction = _ =>
      Controller.changeSpeed() match
        case EngineConstants.ITERATION_MS_1X => text = "x1"
        case EngineConstants.ITERATION_MS_2X => text = "x2"
        case EngineConstants.ITERATION_MS_4X => text = "x4"

  val stepForwardButton = new GenericButton(">>", "Do a single Iteration"):
    disable = true
    onAction = _ =>
      Controller.doSingleIteration()

  private lazy val startSimEventHandler: EventHandler[ActionEvent] = _ =>
    playButton.text = "Pause"
    playButton.tooltip = Tooltip("Pause the simulation")
    playButton.onAction = _ =>
      Controller.isSimulationPlaying() match
        case true =>
          Controller.pauseSimulation()
          stepForwardButton.disable = false
          playButton.text = "Unpause"
          playButton.tooltip = Tooltip("Unpause the simulation")
        case false =>
          Controller.unpauseSimulation()
          stepForwardButton.disable = true
          playButton.text = "Pause"
          playButton.tooltip = Tooltip("Pause the simulation")
    stopButton.setDisable(false)
    changeSpeedButton.setDisable(false)
    DisastersControllerWidget.resetButtons()
    Controller.startSimulation()

  def resetButtons() =
    playButton.onAction = startSimEventHandler
    playButton.setText("Start")
    playButton.tooltip = Tooltip("Start the simulation");
    stopButton.disable = true;
    changeSpeedButton.text = "1x"
    changeSpeedButton.disable = true
    stepForwardButton.disable = true

  val controlBar = new FlowPane:
        background = new Background(Array(new BackgroundFill(Color.Grey, null, null)))
        padding = Insets(10)
        children ++= Seq(playButton, stepForwardButton, changeSpeedButton, stopButton)
        alignment = Pos.Center
