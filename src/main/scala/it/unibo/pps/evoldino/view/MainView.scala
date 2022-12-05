package it.unibo.pps.evoldino.view

import scalafx.application.Platform
import scalafx.stage.Stage
import it.unibo.pps.evoldino.view.View
import it.unibo.pps.evoldino.model.world.WorldSnapshot
import it.unibo.pps.evoldino.view.components.Dialog
import it.unibo.pps.evoldino.view.widgets.ControlBar

class ViewImpl extends View:

  def show(stage: Stage): Unit =
    GUI.start(stage)

  def renderSimulation(snapshot: WorldSnapshot): Unit =
    Platform.runLater(() => GUI.updateRender(snapshot))

  def endSimulation(message: String): Unit =
    Platform.runLater { () =>
      Dialog.dialogBuilder("GAME OVER", message).show()
      ControlBar.resetButtons()
    }
