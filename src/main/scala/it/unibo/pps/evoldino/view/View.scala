package it.unibo.pps.evoldino.view

import scalafx.stage.Stage
import it.unibo.pps.evoldino.model.world.WorldSnapshot

/** View trait implemented in [[ViewImpl]]. */
trait View:

  /** Show the view given a stage for the interface */
  def show(stage: Stage): Unit

  /** Renders on the view a snapshot of the simulation */
  def renderSimulation(snapshot: WorldSnapshot): Unit

  /** Signal the end of the simulation with a dialog */
  def endSimulation(message: String): Unit
