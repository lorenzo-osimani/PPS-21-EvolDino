package it.unibo.pps.evoldino.view

import scalafx.application.Platform
import scalafx.stage.Stage

import it.unibo.pps.evoldino.view.View
import it.unibo.pps.evoldino.model.world.WorldSnapshot

class ViewImpl extends View:

  def show(stage: Stage): Unit =
    GUI.start(stage)

  def renderSimulation(snapshot: WorldSnapshot): Unit =
    Platform.runLater(() => ())
