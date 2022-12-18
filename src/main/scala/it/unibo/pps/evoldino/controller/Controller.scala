package it.unibo.pps.evoldino.controller

import it.unibo.pps.evoldino.controller.engine.Engine
import it.unibo.pps.evoldino.controller.engine.EngineController
import it.unibo.pps.evoldino.model.disaster.Disaster
import it.unibo.pps.evoldino.model.world.{ WorldHistory, WorldSnapshot }
import it.unibo.pps.evoldino.view.View

object Controller:

  var view: Option[View] = Option.empty

  /** Sets the View for the program*/
  def setView(view: View): Unit = this.view = Option(view)

  /** Starts the simulation*/
  def startSimulation(): Unit = Engine.startSimulation()

  /** Stop the simulation manually*/
  def endSimulation(): Unit =
    Engine.endSimulation()
    showEndDialog("The simulation has been stopped manually")

  /** Do a single iteration of the current simulation*/
  def doSingleIteration(): Unit = Engine.doSingleIteration()

  /** Show the dialog with a message to signal that the simulation has ended*/
  def showEndDialog(message: String): Unit =
    view
      .getOrElse(throw IllegalStateException())
      .endSimulation(message)

  /** Pause the current simulation */
  def pauseSimulation(): Unit = Engine.pauseSimulation()

  /** Unpause the current simulation*/
  def unpauseSimulation(): Unit = Engine.unpauseSimulation()

  /** Change the simulation speed*/
  def changeSpeed(): Int = Engine.changeSpeed()

  /**@return true if a simulation is running*/
  def isSimulationPlaying: Boolean = Engine.isSimulationPlaying()

  /**@return true if a simulation is over*/
  def hasSimulationEnded: Boolean = Engine.hasSimulationEnded()

  /**@return true if a simulation is running*/
  def setManualMode(mode: Boolean): Unit = EngineController.setManualMode(mode)

  /**@return true if the manual mode is active*/
  def isManualModeActive: Boolean = EngineController.isManualModeActive

  /** Modify manually the temperature of the environment*/
  def modifyTemp(temp: Float): Unit =
    EngineController.modifyManualSettings(temp)

  /** Modify manually the humidity of the environment*/
  def modifyHum(hum: Float): Unit =
    EngineController.modifyManualSettings(hum = hum)

  /** Modify manually the vegetation level of the environment*/
  def modifyVeg(veg: Float): Unit =
    EngineController.modifyManualSettings(veg = veg)

  /** Modify manually the environment*/
  def modifyManualSettings(temp: Float, hum: Float, veg: Float): Unit =
    EngineController.modifyManualSettings(temp, hum, veg)

  /** Cause manually a disaster in the next iteration*/
  def addDisaster(disaster: Disaster): Unit = EngineController.addDisaster(disaster)

  /** Show on the view an iteration*/
  def renderIteration(snapshot: WorldSnapshot): Unit =
    view.getOrElse(throw IllegalStateException()).renderSimulation(snapshot)
