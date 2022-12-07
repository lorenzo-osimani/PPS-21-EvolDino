package it.unibo.pps.evoldino.controller

import it.unibo.pps.evoldino.controller.engine.Engine
import it.unibo.pps.evoldino.controller.engine.Engine.paused
import it.unibo.pps.evoldino.controller.engine.EngineController
import it.unibo.pps.evoldino.model.world.WorldHistory.isSimulationOver
import it.unibo.pps.evoldino.model.disaster.Disaster
import it.unibo.pps.evoldino.model.world.{ WorldHistory, WorldSnapshot }
import it.unibo.pps.evoldino.view.View

object Controller {

  var view: Option[View] = Option.empty

  def setView(view: View) = this.view = Option(view)

  def startSimulation() = Engine.startSimulation()

  def endSimulation() =
    Engine.endSimulation()
    showEndDialog("The simulation has been stopped manually")

  def doSingleIteration() = Engine.doSingleIteration()

  def showEndDialog(message: String) =
    view
      .getOrElse(throw IllegalStateException())
      .endSimulation(message)

  def pauseSimulation() = Engine.pauseSimulation()

  def unpauseSimulation() = Engine.unpauseSimulation()

  def changeSpeed() = Engine.changeSpeed()

  def isSimulationPlaying(): Boolean = Engine.isSimulationPlaying()

  def hasSimulationEnded(): Boolean = Engine.hasSimulationEnded()

  def setManualMode(mode: Boolean) = EngineController.setManualMode(mode)

  def isManualModeActive() = EngineController.isManualModeActive()

  def modifyTemp(temp: Float) =
    EngineController.modifyManualSettings(temp)

  def modifyHum(hum: Float) =
    EngineController.modifyManualSettings(hum = hum)

  def modifyVeg(veg: Float) =
    EngineController.modifyManualSettings(veg = veg)

  def modifyManualSettings(temp: Float, hum: Float, veg: Float) =
    EngineController.modifyManualSettings(temp, hum, veg)

  def addDisaster(disaster: Disaster) = EngineController.addDisaster(disaster)

  def renderIteration(snapshot: WorldSnapshot): Unit =
    view.getOrElse(throw IllegalStateException()).renderSimulation(snapshot)
}
