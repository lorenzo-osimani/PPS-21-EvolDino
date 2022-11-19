package it.unibo.pps.evoldino.controller

import it.unibo.pps.evoldino.controller.engine.Engine
import it.unibo.pps.evoldino.controller.engine.EngineController

object Controller {
  def startSimulation() = Engine.startSimulation()

  def endSimulation() = Engine.endSimulation()

  def pauseSimulation() = Engine.pauseSimulation()

  def unpauseSimulation() = Engine.unpauseSimulation()

  def changeSpeed() = Engine.changeSpeed()

  def setManualMode(mode: Boolean) = EngineController.setManualMode(mode)

  def isManualModeActive() = EngineController.isManualModeActive()

  def modifyManualSettings(temp: Float, hum: Float, veg: Float) =
    EngineController.modifyManualSettings(temp, hum, veg)
}
