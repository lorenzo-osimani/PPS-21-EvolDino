package it.unibo.pps.evoldino.controller

import it.unibo.pps.evoldino.controller.engine.Engine
import it.unibo.pps.evoldino.controller.engine.Engine.paused
import it.unibo.pps.evoldino.controller.engine.EngineController
import it.unibo.pps.evoldino.controller.engine.WorldHistory.isSimulationOver
import it.unibo.pps.evoldino.model.disaster.Disaster

object Controller {
  def startSimulation() = Engine.startSimulation()

  def endSimulation() = Engine.endSimulation()

  def pauseSimulation() = Engine.pauseSimulation()

  def unpauseSimulation() = Engine.unpauseSimulation()

  def changeSpeed() = Engine.changeSpeed()

  def isSimulationPlaying(): Boolean = Engine.isSimulationPlaying()

  def hasSimulationEnded(): Boolean = Engine.hasSimulationEnded()

  def setManualMode(mode: Boolean) = EngineController.setManualMode(mode)

  def isManualModeActive() = EngineController.isManualModeActive()

  def modifyManualSettings(temp: Float, hum: Float, veg: Float) =
    EngineController.modifyManualSettings(temp, hum, veg)

  /* Add random creation for area damages */
  def addDisaster(disaster: Disaster) = EngineController.addDisaster(disaster)
}
