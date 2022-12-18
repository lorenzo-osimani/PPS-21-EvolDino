package it.unibo.pps.evoldino.controller.engine

import cats.effect.{ExitCode, IO, IOApp, Temporal}
import cats.effect.IO.unit
import cats.effect.unsafe.implicits.global

import scala.concurrent.duration.FiniteDuration
import scala.concurrent.duration
import it.unibo.pps.evoldino.controller.Controller
import EngineConstants.*
import it.unibo.pps.evoldino.model.world.WorldHistory.*

/** Represent the central motor of a simulation*/
object Engine:

  private var iteration_speed: Int = ITERATION_MS_1X

  private var paused = false

  private var ended = false

  /** Starts the simulation resetting all the parameters*/
  def startSimulation(): Unit =
    paused = false
    ended = false
    EngineController.resetController()
    resetHistory()
    Controller.renderIteration(getLastSnapshot())
    simulationLoop().unsafeRunAndForget()

  /** Do a single iteration of the current simulation */
  def doSingleIteration(): Unit =
    iterationLoop().unsafeRunAndForget()

  /** End the current simulation */
  def endSimulation(): Unit = ended = true

  /** Pause the current simulation */
  def pauseSimulation(): Unit = paused = true

  /** Unpause the current simulation and restart the simulation loop*/
  def unpauseSimulation(): Unit =
    if(paused && !hasSimulationEnded())
      paused = false
      simulationLoop().unsafeRunAndForget()

  /** Change the simulation speed*/
  def changeSpeed(): Int =
    iteration_speed match
      case ITERATION_MS_1X => iteration_speed = ITERATION_MS_2X
      case ITERATION_MS_2X => iteration_speed = ITERATION_MS_4X
      case ITERATION_MS_4X => iteration_speed = ITERATION_MS_1X
    iteration_speed

  /** @return true if the simulation is not paused and running*/
  def isSimulationPlaying(): Boolean = !paused && !ended

  /** @return true if the simulation is over*/
  def hasSimulationEnded(): Boolean = isSimulationOver() || ended

  given Conversion[Unit, IO[Unit]] with
    def apply(exp: Unit): IO[Unit] = IO(exp)

  private def simulationLoop(): IO[Unit] = for {
    _ <- Temporal[IO].sleep(FiniteDuration.apply(iteration_speed, duration.MILLISECONDS))
    _ <- iterationLoop()
    _ <- if (hasSimulationEnded() || paused) unit else simulationLoop()
  } yield ()

  private def iterationLoop(): IO[Unit] = for {
    _ <- nextIteration()
    _ <- applyDisturbances()
    _ <- dinosaursPhase()
    _ <- Controller.renderIteration(getLastSnapshot())
    _ <- if (isSimulationOver())
      (getLastSnapshot().livingPopulation().size <= 0) match
        case true  => Controller.showEndDialog("All the dinosaurs are dead")
        case false => Controller.showEndDialog("The world has reached its maximum lifespan")
  } yield ()

