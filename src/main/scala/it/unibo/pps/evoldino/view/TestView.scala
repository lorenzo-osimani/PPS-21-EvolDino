package it.unibo.pps.evoldino.view

import it.unibo.pps.evoldino.model.world.WorldSnapshot
import scalafx.stage.Stage

/** View trait implemented in [[ViewImpl]]. This is a dummy implementation for the tests. */
class TestView extends View:

  def show(stage: Stage): Unit = ()

  def renderSimulation(snapshot: WorldSnapshot): Unit = ()

  def endSimulation(message: String): Unit = ()
