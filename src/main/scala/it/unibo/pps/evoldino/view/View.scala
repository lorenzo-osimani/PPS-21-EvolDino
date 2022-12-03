package it.unibo.pps.evoldino.view

import scalafx.stage.Stage
import it.unibo.pps.evoldino.model.world.WorldSnapshot

/** View trait implemented in [[ViewImpl]]. */
trait View:

  def show(stage: Stage): Unit

  def renderSimulation(snapshot: WorldSnapshot): Unit

  def endSimulation(message: String): Unit
