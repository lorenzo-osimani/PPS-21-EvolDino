package it.unibo.pps.evoldino

import it.unibo.pps.evoldino.controller.Controller
import scalafx.application.JFXApp3
import it.unibo.pps.evoldino.view.ViewImpl

object EvoldinoApp extends JFXApp3:
  val view = ViewImpl()

  /*@main Entry point*/
  override def start(): Unit =
    Controller.setView(view)
    view.show(new JFXApp3.PrimaryStage())

  /** Automatically stops the simulation when closing frame. */
  override def stopApp(): Unit =
    Controller.endSimulation()
